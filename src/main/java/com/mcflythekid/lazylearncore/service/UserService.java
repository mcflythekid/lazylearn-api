package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.entity.User;
import com.mcflythekid.lazylearncore.exception.AppForbiddenException;
import com.mcflythekid.lazylearncore.exception.AppNotFoundException;
import com.mcflythekid.lazylearncore.indto.UserChangePasswordInDto;
import com.mcflythekid.lazylearncore.indto.UserRegisterInDto;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.outdto.UserRegisterOutDto;
import com.mcflythekid.lazylearncore.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Date;

/**
 * @author McFly the Kid
 */
@Service
public class UserService {

    public UserRegisterOutDto register(UserRegisterInDto userRegisterInDto){
        User user = userRegisterInDto.getUser();
        user.setCreatedOn(new Date());
        user.setHashedPassword(authService.hashPassword(user.getPassword()));
        user.setId(authService.getRamdomId());
        userRepo.save(user);
        return new UserRegisterOutDto(user);
    }

    @PutMapping
    public JSON changePassword(String userId, UserChangePasswordInDto userChangePasswordInDto){
        User user = userRepo.findOne(userId);
        if (user == null) throw new AppNotFoundException("User not found");
        if (!authService.isPasswordValid(userChangePasswordInDto.getOldPassword(), user))
            throw new AppForbiddenException("Old password is wrong");
        user.setHashedPassword(authService.hashPassword(userChangePasswordInDto.getNewPassword()));
        user.setUpdatedOn(new Date());
        userRepo.save(user);
        return JSON.ok();
    }

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthService authService;
}
