package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.Const;
import com.mcflythekid.lazylearncore.entity.ForgetPassword;
import com.mcflythekid.lazylearncore.entity.User;
import com.mcflythekid.lazylearncore.exception.AppConflictException;
import com.mcflythekid.lazylearncore.exception.AppForbiddenException;
import com.mcflythekid.lazylearncore.exception.AppNotFoundException;
import com.mcflythekid.lazylearncore.exception.AppUnauthorizedException;
import com.mcflythekid.lazylearncore.indto.UserChangePasswordInDto;
import com.mcflythekid.lazylearncore.indto.UserRegisterInDto;
import com.mcflythekid.lazylearncore.indto.UserResetPasswordInDto;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.outdto.UserRegisterOutDto;
import com.mcflythekid.lazylearncore.repo.ForgetPasswordRepo;
import com.mcflythekid.lazylearncore.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import javax.swing.*;
import java.util.Date;

/**
 * @author McFly the Kid
 */
@Service
public class UserService {

    public UserRegisterOutDto register(UserRegisterInDto userRegisterInDto){
        if (userRepo.findByEmail(userRegisterInDto.getEmail()) != null)
            throw new AppConflictException("Email address already exists");

        User user = userRegisterInDto.getUser();
        user.setCreatedOn(new Date());
        user.setHashedPassword(authService.hashPassword(user.getPassword()));
        user.setId(authService.getRamdomId());
        userRepo.save(user);
        return new UserRegisterOutDto(user);
    }

    public JSON changePassword(String userId, UserChangePasswordInDto userChangePasswordInDto){
        User user = userRepo.findOne(userId);
        if (user == null) throw new AppNotFoundException("User not found");

        if(!user.getEmail().equals(authService.getCurrentUserEmail()))
            throw new AppForbiddenException("Fuck it, fuck your mom");

        if (!authService.isPasswordValid(userChangePasswordInDto.getOldPassword(), user))
            throw new AppUnauthorizedException("Old password is wrong");
        user.setHashedPassword(authService.hashPassword(userChangePasswordInDto.getNewPassword()));
        user.setUpdatedOn(new Date());
        userRepo.save(user);
        return JSON.ok();
    }

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ForgetPasswordRepo forgetPasswordRepo;

    @Autowired
    private AuthService authService;

    public JSON resetPassword(String forgetPasswordId, UserResetPasswordInDto userResetPasswordInDto) {
        ForgetPassword forgetPassword = forgetPasswordRepo.findOne(forgetPasswordId);
        if (forgetPassword == null) throw new AppNotFoundException("Request doesn't exists");

        if (forgetPassword.getStatus() == Const.FORGETPASSWORD_STATUS_USED) throw new AppConflictException("Already used");
        if (forgetPassword.getExpiredOn().before(new Date())) throw new AppConflictException("Already expired");

        User user = userRepo.findOne(forgetPassword.getUserId());
        if (user == null) throw new AppNotFoundException("User doesn't not exists anymore");

        forgetPassword.setStatus(Const.FORGETPASSWORD_STATUS_USED);
        forgetPassword.setUpdatedOn(new Date());

        user.setHashedPassword(authService.hashPassword(userResetPasswordInDto.getPassword()));
        userRepo.save(user);

        return JSON.ok();
    }
}