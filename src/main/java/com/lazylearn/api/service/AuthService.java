package com.lazylearn.api.service;

import com.lazylearn.api.config.Consts;
import com.lazylearn.api.config.exception.AppException;
import com.lazylearn.api.config.jwt.JWTTokenProvider;
import com.lazylearn.api.entity.Deck;
import com.lazylearn.api.entity.Reset;
import com.lazylearn.api.entity.Session;
import com.lazylearn.api.entity.User;
import com.lazylearn.api.indto.ClientData;
import com.lazylearn.api.indto.auth.*;
import com.lazylearn.api.outdto.LoginOut;
import com.lazylearn.api.outdto.UserData;
import com.lazylearn.api.repo.ForgetPasswordRepo;
import com.lazylearn.api.repo.SessionRepo;
import com.lazylearn.api.repo.UserRepo;
import com.lazylearn.api.util.EmailUtils;
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

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    @Autowired
    private SessionRepo sessionRepo;
    @Autowired
    private DeckService deckService;

    @Transactional(rollbackFor = Exception.class)
    public void forgetPassword(ForgetPasswordIn in) {
        User user = userRepo.findByEmail(in.getEmail());
        if (user == null) throw new AppException(404, "Email not found");

        Reset forgetPassword = new Reset();
        forgetPassword.setUserId(user.getId());
        forgetPassword.setExpiredDate(DateUtils.addDays(new Date(), Consts.FORGETPASSWORD_EXPIRED_DAYS));
        forgetPasswordRepo.save(forgetPassword);

        EmailUtils.sendHtmlEmail("support@lazylearn.com", "Lazylearn Team",
                in.getEmail(), "Reset your password",  createResetPasswordEmailContent(forgetPassword));
    }

    @Transactional(rollbackFor = Exception.class)
    public LoginOut resetPassword(ResetPasswordIn in, ClientData clientData) {
        Reset forgetPassword = forgetPasswordRepo.findOne(in.getResetId());
        if (forgetPassword == null) throw new AppException(404, "Reset password request doesn't exists or used");
        if (forgetPassword.getExpiredDate().before(new Date())) throw new AppException(403, "Already expired");

        User user = userRepo.findOne(forgetPassword.getUserId());
        if (user == null) throw new AppException("User doesn't not exists anymore");

        user.setEncodedPassword(passwordEncoder.encode(in.getNewRawPassword()));
        userRepo.save(user);

        forgetPasswordRepo.deleteByUserId(user.getId());

        return createLoginResponse(user, clientData);
    }

    @Transactional(rollbackFor = Exception.class)
    public LoginOut register(RegisterIn registerIn, ClientData clientData) throws IOException {
        if (userRepo.findByEmail(registerIn.getEmail()) != null) throw new AppException(HttpStatus.CONFLICT.value(), "Email address already exists");

        User user = new User();
        user.setEmail(registerIn.getEmail());
        user.setEncodedPassword(passwordEncoder.encode(registerIn.getRawPassword()));
        user.setFullName(user.getEmail().split("@")[0]);
        user.setIpAddress(clientData.getIpAddress());

        user = userRepo.save(user);
        createTemplateDeck(user.getId());

        authorityService.createAuthority(user.getId(), Consts.AUTHORITY_DEFAULT);

        return createLoginResponse(user, clientData);
    }

    public LoginOut login(LoginIn authLoginInDto, ClientData clientData){
        User user = userRepo.findByEmail(authLoginInDto.getEmail());
        if (user == null){
            throw new AppException("Email not found");
        }
        if (!passwordEncoder.matches(authLoginInDto.getRawPassword(), user.getEncodedPassword())){
            throw new AppException("Wrong password");
        }
        return createLoginResponse(user, clientData);
    }

    @Transactional(rollbackFor = Exception.class)
    public LoginOut loginFacebook(LoginFacebookIn in, ClientData clientData) throws IOException {
        FacebookClient facebookClient = new DefaultFacebookClient(in.getAccessToken(), Version.VERSION_3_0);
        com.restfb.types.User fbUser = facebookClient.fetchObject("me",  com.restfb.types.User.class,Parameter.with("fields", "name,id"));

        User user = userRepo.findByFacebookId(fbUser.getId());
        if (user == null){
            user = new User();
            user.setIpAddress(clientData.getIpAddress());
            user.setFacebookId(fbUser.getId());
            user.setFullName(fbUser.getName());
            user = userRepo.save(user);
            createTemplateDeck(user.getId());

            authorityService.createAuthority(user.getId(), Consts.AUTHORITY_DEFAULT);
            authorityService.createAuthority(user.getId(), Consts.AUTHORITY_FACEBOOK);
        }

        return createLoginResponse(user, clientData);
    }

    @Transactional(rollbackFor = Exception.class)
    public LoginOut logoutAllSession(String userId, ClientData clientData){
        User user = userRepo.findOne(userId);
        user.setSessionKey(UUID.randomUUID().toString());
        userRepo.save(user);

        sessionRepo.deleteAllByUserId(userId);

        return createLoginResponse(user, clientData);
    }

    @Transactional(rollbackFor = Exception.class)
    public void changePassword(String userId, String rawPassword){
        User user = userRepo.findOne(userId);
        user.setEncodedPassword(passwordEncoder.encode(rawPassword));
        userRepo.save(user);
    }

    public String getAllSession(String userId){
        List<Session> sessions = sessionRepo.findAllByUserIdOrderByCreatedDateDesc(userId);
        return sessions.stream().map(Session::getClientData).collect(Collectors.joining("<br>"));
    }

    private LoginOut createLoginResponse(User user, ClientData clientData){
        UserData userData = new UserData();
        userData.setEmail(user.getEmail());
        userData.setUserId(user.getId());
        userData.setFullName(user.getFullName());
        userData.setAuthorities(authorityService.getUserAuthorities(user.getId()));

        LoginOut loginOut = new LoginOut();
        loginOut.setAccessToken(jwtTokenProvider.createToken(user, clientData));
        loginOut.setUserData(userData);
        return loginOut;
    }

    private String createResetPasswordEmailContent(Reset forgetPassword){
        String url = String.format(resetPasswordUrlFormat, forgetPassword.getId());
        return String.format("<a href='%s'>Please click here</a>", url);
    }

    private Deck createTemplateDeck(String userId) throws IOException {
        final String TEMPLATE_ID = "3000.txt";
        return deckService.importDeck(TEMPLATE_ID, userId);
    }
}