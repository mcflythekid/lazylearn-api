package com.lazylearn.api.controller;

import java.io.IOException;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lazylearn.api.indto.auth.ChangePasswordIn;
import com.lazylearn.api.indto.auth.ForgetPasswordIn;
import com.lazylearn.api.indto.auth.LoginFacebookIn;
import com.lazylearn.api.indto.auth.LoginIn;
import com.lazylearn.api.indto.auth.RegisterIn;
import com.lazylearn.api.indto.auth.ResetPasswordIn;
import com.lazylearn.api.outdto.JSON;
import com.lazylearn.api.outdto.LoginOut;
import com.lazylearn.api.service.AuthService;

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
    public LoginOut register(@Valid @RequestBody RegisterIn in) throws Exception {
        return authService.register(in, getClientData());
    }

    @PostMapping("/login")
    public LoginOut login(@Valid @RequestBody LoginIn in){
        return authService.login(in,getClientData());
    }

    @PostMapping("/force-login/{userId}")
    public LoginOut forceLogin(@PathVariable String userId){
        return authService.forceLogin(userId, getClientData());
    }

    @PostMapping("/login-facebook")
    public LoginOut loginFacebook(@Valid @RequestBody LoginFacebookIn in) throws Exception {
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