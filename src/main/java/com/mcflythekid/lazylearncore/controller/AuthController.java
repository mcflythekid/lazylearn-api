package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.config.jwt.JWTTokenProvider;
import com.mcflythekid.lazylearncore.indto.*;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.repo.UserAuthorityRepo;
import com.mcflythekid.lazylearncore.repo.UserRepo;
import com.mcflythekid.lazylearncore.service.AuthService;
import com.mcflythekid.lazylearncore.service.AuthorityService;
import com.mcflythekid.lazylearncore.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author McFly the Kid
 */
@RestController
public class AuthController extends BaseController{

    @Autowired
    private AuthService authService;

    @PostMapping("/forget-password")
    public JSON forgetPassword(@Valid @RequestBody ForgetPasswordIn in) {
        authService.forgetPassword(in);
        return JSON.ok();
    }

    @PutMapping("/reset-password")
    public JSON resetPassword(@Valid @RequestBody ResetPasswordIn in) {
        authService.resetPassword(in);
        return JSON.ok();
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterIn in){
        return authService.register(in, getIpAddress());
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginIn in){
        return authService.login(in);
    }

    @PostMapping("/login-facebook")
    public String loginFacebook(@Valid @RequestBody LoginFacebookIn in){
        return authService.loginFacebook(in, getIpAddress());
    }

    /*************************************************************************************************/
    /*************************************************************************************************/
    /*************************************************************************************************/

    @PostMapping("/ping")
    public JSON ping(){
        return JSON.ok();
    }

    @PostMapping("/logout-all-session")
    public String logoutAllSession() throws Exception {
        return authService.logoutAllSession(SecurityUtils.getCurrentUserLogin());
    }

    @PutMapping("/change-password")
    public JSON changePassword(@Valid @RequestBody ChangePasswordIn in) throws Exception {
        authService.changePassword(SecurityUtils.getCurrentUserLogin(), in.getRawPassword());
        return JSON.ok();
    }
}