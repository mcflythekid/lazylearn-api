package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.indto.UserResetPasswordInDto;
import com.mcflythekid.lazylearncore.indto.UserChangePasswordInDto;
import com.mcflythekid.lazylearncore.indto.UserRegisterInDto;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.outdto.UserRegisterOutDto;
import com.mcflythekid.lazylearncore.service.RequestService;
import com.mcflythekid.lazylearncore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author McFly the Kid
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @PostMapping
    public UserRegisterOutDto register(@Valid @RequestBody UserRegisterInDto userRegisterInDto){
        userRegisterInDto.setRegisterIpAddress(requestService.getIpAddress());
        return userService.register(userRegisterInDto);
    }

    @PutMapping("/{userId}/password")
    public JSON changePassword(
            @PathVariable("userId") String userId,
            @Valid @RequestBody UserChangePasswordInDto userChangePasswordInDto){
        return userService.changePassword(userId, userChangePasswordInDto);
    }

    @PutMapping("/by-forget-password/{forgetPasswordId}")
    public JSON resetPassword(
            @PathVariable("forgetPasswordId") String forgetPasswordId,
            @Valid @RequestBody UserResetPasswordInDto userResetPasswordInDto){
        return userService.resetPassword(forgetPasswordId, userResetPasswordInDto);
    }

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;
}
