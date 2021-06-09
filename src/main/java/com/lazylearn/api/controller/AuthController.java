package com.lazylearn.api.controller;

import com.lazylearn.api.config.exception.AppException;
import com.lazylearn.api.entity.User;
import com.lazylearn.api.indto.ChangeEmailIn;
import com.lazylearn.api.indto.ChangeNameIn;
import com.lazylearn.api.indto.ChangeTimezoneIn;
import com.lazylearn.api.indto.auth.*;
import com.lazylearn.api.outdto.JSON;
import com.lazylearn.api.outdto.LoginOut;
import com.lazylearn.api.outdto.SettingOut;
import com.lazylearn.api.repo.UserRepo;
import com.lazylearn.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

/**
 * @author McFly the Kid
 */
@RestController
public class AuthController extends BaseController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/get-setting")
    public SettingOut getSetting() throws Exception {
        User user = userRepo.findOne(getUserId());
        return SettingOut.builder()
                .email(user.getEmail())
                .timezone(user.getTimezone())
                .name(user.getFullName())
                .build();
    }

    @PostMapping("/change-email")
    public JSON changeEmail(@RequestBody @Valid ChangeEmailIn payload) throws Exception {
        User duplicatedUser = userRepo.findByEmail(payload.getEmail());
        if (duplicatedUser != null) {
            if (duplicatedUser.getId().equalsIgnoreCase(getUserId())) {
                return JSON.ok("Nothing changed");
            }
            throw new AppException(401, "Email address already in use");
        }

        User actualUser = userRepo.findOne(getUserId());
        actualUser.setEmail(payload.getEmail());
        userRepo.save(actualUser);

        return JSON.ok("Email changed");
    }

    @PostMapping("/change-timezone")
    public JSON changeTimezone(@RequestBody @Valid ChangeTimezoneIn payload) throws Exception {
        User actualUser = userRepo.findOne(getUserId());
        actualUser.setTimezone(payload.getTimezone());
        userRepo.save(actualUser);

        return JSON.ok("Timezone changed");
    }

    @PostMapping("/change-name")
    public JSON changeName(@RequestBody @Valid ChangeNameIn payload) throws Exception {
        User actualUser = userRepo.findOne(getUserId());
        actualUser.setFullName(payload.getName());
        userRepo.save(actualUser);

        return JSON.ok("Name changed, login again to take affect");
    }

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
    public LoginOut login(@Valid @RequestBody LoginIn in) {
        return authService.login(in, getClientData());
    }

    @PostMapping("/force-login/{userId}")
    public LoginOut forceLogin(@PathVariable String userId) {
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
    public String getAllSession() throws Exception {
        return authService.getAllSession(getUserId());
    }

    @PostMapping("/logout-all-session")
    public LoginOut logoutAllSession() throws Exception {
        return authService.logoutAllSession(getUserId(), getClientData());
    }

    @PostMapping("/change-password")
    public JSON changePassword(@Valid @RequestBody ChangePasswordIn in) throws Exception {
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        authService.changePassword(getUserId(), in.getNewRawPassword());
        return JSON.ok("Change password success");
    }
}