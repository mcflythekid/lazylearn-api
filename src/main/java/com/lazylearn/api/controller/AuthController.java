package com.lazylearn.api.controller;

import com.lazylearn.api.indto.auth.*;
import com.mcflythekid.lazylearncore.indto.auth.*;
import com.lazylearn.api.outdto.JSON;
import com.lazylearn.api.outdto.LoginOut;
import com.lazylearn.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
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