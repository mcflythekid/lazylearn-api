package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.config.exception.AppException;
import com.mcflythekid.lazylearncore.indto.*;
import com.mcflythekid.lazylearncore.outdto.AuthLoginOutDto;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.outdto.UserRegisterOutDto;
import com.mcflythekid.lazylearncore.service.AuthService;
import com.mcflythekid.lazylearncore.service.ForgetPasswordService;
import com.mcflythekid.lazylearncore.service.UserService;
import com.mcflythekid.lazylearncore.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author McFly the Kid
 */
@RestController
public class AuthController extends BaseController{

    @Autowired
    private AuthService authService;
    @Autowired
    private ForgetPasswordService forgetPasswordService;
    @Autowired
    private UserService userService;

    @PostMapping("/ping")
    public JSON ping(){
        return JSON.ok();
    }

    @PostMapping("/login")
    public AuthLoginOutDto login(@Valid @RequestBody AuthLoginInDto payload){
        return authService.login(payload);
    }

    @PostMapping("/login-facebook")
    public AuthLoginOutDto loginFacebook(@Valid @RequestBody FacebookLoginInDto payload){
        AuthLoginInDto x = new AuthLoginInDto();
        x.setEmail("odopoc@gmail.com");
        x.setPassword("dkmm");
        return login(x);
    }
    
    @PostMapping("/logout-other-session")
    public AuthLoginOutDto logoutOtherSession() throws Exception {
        return authService.logoutOtherSession(SecurityUtils.getCurrentUserLogin());
    }

    @PostMapping("/forget-password")
    public JSON create(@Valid @RequestBody ForgetPasswordCreateInDto forgetPasswordCreateInDto) {
        forgetPasswordCreateInDto.setIpAddress(getIpAddress());
        return forgetPasswordService.create(forgetPasswordCreateInDto);
    }

    @PutMapping("/user/by-forget-password/{forgetPasswordId}/password")
    public JSON resetPassword(
            @PathVariable("forgetPasswordId") String forgetPasswordId,
            @Valid @RequestBody UserResetPasswordInDto userResetPasswordInDto) {
        return userService.resetPassword(forgetPasswordId, userResetPasswordInDto);
    }

    @PostMapping("/register")
    public UserRegisterOutDto register(@Valid @RequestBody UserRegisterInDto userRegisterInDto){
        userRegisterInDto.setRegisterIpAddress(getIpAddress());
        return userService.register(userRegisterInDto);
    }

    @PutMapping("/user/{userId}/password")
    public JSON changePassword(@PathVariable("userId") String userId,
                               @Valid @RequestBody UserChangePasswordInDto userChangePasswordInDto) throws Exception {
        authorizeUser(userId);

        userChangePasswordInDto.setUserId(userId);
        return userService.changePassword(userChangePasswordInDto);
    }
}