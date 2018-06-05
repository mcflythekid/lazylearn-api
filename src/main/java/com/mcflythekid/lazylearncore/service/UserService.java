package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.config.Consts;
import com.mcflythekid.lazylearncore.config.jwt.JWTTokenProvider;
import com.mcflythekid.lazylearncore.entity.ForgetPassword;
import com.mcflythekid.lazylearncore.entity.User;
import com.mcflythekid.lazylearncore.config.exception.AppException;
import com.mcflythekid.lazylearncore.indto.SearchUserInDto;
import com.mcflythekid.lazylearncore.indto.UserChangePasswordInDto;
import com.mcflythekid.lazylearncore.indto.UserRegisterInDto;
import com.mcflythekid.lazylearncore.indto.UserResetPasswordInDto;
import com.mcflythekid.lazylearncore.outdto.AuthLoginOutDto;
import com.mcflythekid.lazylearncore.outdto.BootstrapTableOutDto;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.repo.ForgetPasswordRepo;
import com.mcflythekid.lazylearncore.repo.UserRepo;
import com.mcflythekid.lazylearncore.repo.VUserRepo;
import com.mcflythekid.lazylearncore.util.StringUtils2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author McFly the Kid
 */
@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private VUserRepo vuserRepo;
    @Autowired
    private ForgetPasswordRepo forgetPasswordRepo;
    @Autowired
    private JWTTokenProvider jWTTokenProvider;

    @Transactional(rollbackFor = Exception.class)
    public AuthLoginOutDto register(UserRegisterInDto userRegisterInDto){
        if (userRepo.findByEmail(userRegisterInDto.getEmail()) != null) throw new AppException("Email address already exists");

        User user = userRegisterInDto.getUser();
        user.setCreatedOn(new Date());
        user.setHashedPassword(passwordEncoder.encode(user.getPassword()));
        user.setId(StringUtils2.generateRandomId());
        user.setJtv(UUID.randomUUID().toString());
        userRepo.save(user);

        String token = jWTTokenProvider.createToken(user);
        return new AuthLoginOutDto(token, user.getId(), user.getEmail());
    }

    @Transactional(rollbackFor = Exception.class)
    public JSON changePassword(UserChangePasswordInDto userChangePasswordInDto){
        User user = userRepo.findOne(userChangePasswordInDto.getUserId());
        user.setHashedPassword(passwordEncoder.encode(userChangePasswordInDto.getNewPassword()));
        user.setUpdatedOn(new Date());
        user.setJtv(UUID.randomUUID().toString());
        userRepo.save(user);
        return JSON.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public JSON resetPassword(String forgetPasswordId, UserResetPasswordInDto userResetPasswordInDto) {
        ForgetPassword forgetPassword = forgetPasswordRepo.findOne(forgetPasswordId);
        if (forgetPassword == null) throw new AppException("Request doesn't exists");

        if (forgetPassword.getStatus() == Consts.FORGETPASSWORD_STATUS_USED) throw new AppException("Already used");
        if (forgetPassword.getExpiredOn().before(new Date())) throw new AppException("Already expired");

        User user = userRepo.findOne(forgetPassword.getUserId());
        if (user == null) throw new AppException("User doesn't not exists anymore");

        forgetPassword.setStatus(Consts.FORGETPASSWORD_STATUS_USED);
        forgetPassword.setUpdatedOn(new Date());

        user.setHashedPassword(passwordEncoder.encode(userResetPasswordInDto.getPassword()));
        user.setUpdatedOn(new Date());
        user.setJtv(UUID.randomUUID().toString());
        userRepo.save(user);

        return JSON.ok();
    }

    public BootstrapTableOutDto search(SearchUserInDto searchUserInDto){
        List rows = vuserRepo.findAllByEmail(searchUserInDto.getSearch(), searchUserInDto.getPageable());
        Long total = vuserRepo.countAllByEmail(searchUserInDto.getSearch());
        return new BootstrapTableOutDto(rows, total);
    }
}