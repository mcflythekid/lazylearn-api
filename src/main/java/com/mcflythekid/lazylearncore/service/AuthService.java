package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.config.Consts;
import com.mcflythekid.lazylearncore.config.exception.AppException;
import com.mcflythekid.lazylearncore.config.jwt.JWTTokenProvider;
import com.mcflythekid.lazylearncore.entity.ForgetPassword;
import com.mcflythekid.lazylearncore.entity.User;
import com.mcflythekid.lazylearncore.indto.*;
import com.mcflythekid.lazylearncore.repo.ForgetPasswordRepo;
import com.mcflythekid.lazylearncore.repo.UserAuthorityRepo;
import com.mcflythekid.lazylearncore.repo.UserRepo;
import com.mcflythekid.lazylearncore.repo.VUserRepo;
import com.mcflythekid.lazylearncore.util.EmailUtils;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * @author McFly the Kid
 */
@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ForgetPasswordRepo forgetPasswordRepo;
    @Value("${format.reset-password-url}")
    private String resetPasswordUrlFormat;
    @Autowired
    private JWTTokenProvider jwtTokenProvider;
    @Autowired
    private AuthorityService authorityService;

    @Transactional(rollbackFor = Exception.class)
    public void forgetPassword(ForgetPasswordIn in) {
        User user = userRepo.findByEmail(in.getEmail());
        if (user == null) throw new AppException(404, "Email not found");

        ForgetPassword forgetPassword = new ForgetPassword();
        forgetPassword.setUserId(user.getId());
        forgetPassword.setExpiredDate(DateUtils.addDays(new Date(), Consts.FORGETPASSWORD_EXPIRED_DAYS));

        EmailUtils.sendHtmlEmail("support@lazylearn.com", "Lazylearn Team",
                in.getEmail(), "Reset your password",  createResetPasswordEmailContent(forgetPassword));

        forgetPasswordRepo.save(forgetPassword);
    }

    @Transactional(rollbackFor = Exception.class)
    public void resetPassword(ResetPasswordIn in) {
        ForgetPassword forgetPassword = forgetPasswordRepo.findOne(in.getForgetPasswordId());
        if (forgetPassword == null) throw new AppException(404, "Request doesn't exists");
        if (forgetPassword.getExpiredDate().before(new Date())) throw new AppException(403, "Already expired");

        User user = userRepo.findOne(forgetPassword.getUserId());
        if (user == null) throw new AppException("User doesn't not exists anymore");

        user.setEncodedPassword(passwordEncoder.encode(in.getRawPassword()));
        userRepo.save(user);

        forgetPasswordRepo.deleteByUserId(user.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    public String register(RegisterIn registerIn, String ipAddress){
        if (userRepo.findByEmail(registerIn.getEmail()) != null) throw new AppException(HttpStatus.CONFLICT.value(), "Email address already exists");

        User user = new User();
        user.setEmail(registerIn.getEmail());
        user.setEncodedPassword(passwordEncoder.encode(registerIn.getRawPassword()));
        user.setFullName(user.getEmail().split("@")[0]);

        userRepo.save(user);

        authorityService.createAuthority(user.getId(), Consts.AUTHORITY_DEFAULT);

        return jwtTokenProvider.createToken(user);
    }

    public String login(LoginIn authLoginInDto){
        User user = userRepo.findByEmail(authLoginInDto.getEmail());
        if (user == null){
            throw new AppException("Email not found");
        }
        if (!passwordEncoder.matches(authLoginInDto.getPassword(), user.getEncodedPassword())){
            throw new AppException("Wrong password");
        }
        return jwtTokenProvider.createToken(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public String loginFacebook(LoginFacebookIn in, String ipAddress){
        FacebookClient facebookClient = new DefaultFacebookClient(in.getAccessToken(), Version.VERSION_3_0);
        com.restfb.types.User fbUser = facebookClient.fetchObject("me",  com.restfb.types.User.class,Parameter.with("fields", "name,id"));

        User user = userRepo.findByFacebookId(fbUser.getId());
        if (user == null){
            user = new User();
            user.setIpAddress(ipAddress);
            user.setFacebookId(fbUser.getId());
            user.setFullName(fbUser.getName());
            user = userRepo.save(user);

            authorityService.createAuthority(user.getId(), Consts.AUTHORITY_DEFAULT);
            authorityService.createAuthority(user.getId(), Consts.AUTHORITY_FACEBOOK);
        }

        return jwtTokenProvider.createToken(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public String logoutAllSession(String userId){
        User user = userRepo.findOne(userId);
        user.setJtv(UUID.randomUUID().toString());
        userRepo.save(user);

        return jwtTokenProvider.createToken(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void changePassword(String userId, String rawPassword){
        User user = userRepo.findOne(userId);
        user.setEncodedPassword(passwordEncoder.encode(rawPassword));
        userRepo.save(user);
    }

    private String createResetPasswordEmailContent(ForgetPassword forgetPassword){
        String url = String.format(resetPasswordUrlFormat, forgetPassword.getId());
        return String.format("<a href='%s'>Please click here</a>", url);
    }
}