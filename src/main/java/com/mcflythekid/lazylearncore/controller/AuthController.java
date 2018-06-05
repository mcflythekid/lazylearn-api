package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.config.Consts;
import com.mcflythekid.lazylearncore.config.jwt.JWTTokenProvider;
import com.mcflythekid.lazylearncore.entity.User;
import com.mcflythekid.lazylearncore.entity.UserAuthority;
import com.mcflythekid.lazylearncore.entity.key.UserAuthorityKey;
import com.mcflythekid.lazylearncore.indto.*;
import com.mcflythekid.lazylearncore.outdto.AuthLoginOutDto;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.repo.UserAuthorityRepo;
import com.mcflythekid.lazylearncore.repo.UserRepo;
import com.mcflythekid.lazylearncore.service.AuthService;
import com.mcflythekid.lazylearncore.service.ForgetPasswordService;
import com.mcflythekid.lazylearncore.service.UserService;
import com.mcflythekid.lazylearncore.util.SecurityUtils;
import com.mcflythekid.lazylearncore.util.StringUtils2;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserAuthorityRepo userAuthorityRepo;
    @Autowired
    private JWTTokenProvider jwtTokenProvider;

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

//        User_Profile obj_User_Profile=new User_Profile();
        FacebookClient facebookClient = new DefaultFacebookClient(payload.getAccessToken(), Version.VERSION_3_0);
        //com.restfb.types.User fbUser = facebookClient.fetchObject("/me?fields=name,id,email", com.restfb.types.User.class);
        com.restfb.types.User fbUser = facebookClient.fetchObject("me",  com.restfb.types.User.class,Parameter.with("fields", "email,name,id"));

        User user = userRepo.findByFacebookId(fbUser.getId());
        if (user == null){
            user = new User();
            user.setId(StringUtils2.generateRandomId());
            user.setRegisterIpAddress(getIpAddress());
            user.setCreatedOn(new Date());
            user.setJtv(UUID.randomUUID().toString());
            user.setFacebookId(fbUser.getId());
            user.setEmail(fbUser.getEmail());
            user = userRepo.save(user);

            UserAuthority userAuthority = new UserAuthority();
            UserAuthorityKey userAuthorityKey = new UserAuthorityKey();
            userAuthorityKey.setAuthority(Consts.AUTHORITY_DEFAULT);
            userAuthorityKey.setUserId(user.getId());
            userAuthority.setKey(userAuthorityKey);
            userAuthorityRepo.save(userAuthority);

            UserAuthority userAuthority2 = new UserAuthority();
            UserAuthorityKey userAuthorityKey2 = new UserAuthorityKey();
            userAuthorityKey2.setAuthority(Consts.AUTHORITY_FACEBOOK);
            userAuthorityKey2.setUserId(user.getId());
            userAuthority2.setKey(userAuthorityKey2);
            userAuthorityRepo.save(userAuthority2);
        }


        String token = jwtTokenProvider.createToken(user);
        return new AuthLoginOutDto(token, user.getId(), user.getEmail());

        //facebookClient.
//        System.out.println("User name: " + user.getName());
//        obj_User_Profile.setUser_name(user.getName());
//        return obj_User_Profile;

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
    public AuthLoginOutDto register(@Valid @RequestBody UserRegisterInDto userRegisterInDto){
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