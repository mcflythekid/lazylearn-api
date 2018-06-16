package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.indto.*;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.outdto.LoginOut;
import com.mcflythekid.lazylearncore.service.AuthService;
import com.mcflythekid.lazylearncore.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

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
        return JSON.ok("An email was sent to " + in.getEmail());
    }

    @PostMapping("/reset-password")
    public LoginOut resetPassword(@Valid @RequestBody ResetPasswordIn in) {
        return authService.resetPassword(in, getClientData());
    }

    @PostMapping("/register")
    public LoginOut register(@Valid @RequestBody RegisterIn in){
        return authService.register(in, getClientData());
    }

    @PostMapping("/login")
    public LoginOut login(@Valid @RequestBody LoginIn in){
        return authService.login(in,getClientData());
    }

    @PostMapping("/login-facebook")
    public LoginOut loginFacebook(@Valid @RequestBody LoginFacebookIn in){
        return authService.loginFacebook(in, getClientData());
    }

    /*************************************************************************************************/
    /*************************************************************************************************/
    /*************************************************************************************************/

    @PostMapping("/get-all-session")
    public String getAllSession() throws Exception{
        return authService.getAllSession(getUserId());
    }

    @PostMapping("/ping")
    public JSON ping(){
        return JSON.ok();
    }

    @PostMapping("/logout-all-session")
    public LoginOut logoutAllSession() throws Exception {
        return authService.logoutAllSession(getUserId(), getClientData());
    }

    @PostMapping("/change-password")
    public JSON changePassword(@Valid @RequestBody ChangePasswordIn in) throws Exception {
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        authService.changePassword(getUserId(), in.getNewRawPassword());
        return JSON.ok("Change password success");
    }
}