package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.entity.User;
import com.mcflythekid.lazylearncore.indto.UserChangePasswordInDto;
import com.mcflythekid.lazylearncore.indto.UserRegisterInDto;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.outdto.UserRegisterOutDto;
import com.mcflythekid.lazylearncore.service.AuthService;
import com.mcflythekid.lazylearncore.service.RequestService;
import com.mcflythekid.lazylearncore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author McFly the Kid
 */
@RestController
public class UserController extends BaseController {

    @PostMapping("/user")
    public UserRegisterOutDto register(@Valid @RequestBody UserRegisterInDto userRegisterInDto){
        userRegisterInDto.setRegisterIpAddress(requestService.getIpAddress());
        return userService.register(userRegisterInDto);
    }

    @PutMapping("/user/{userId}/password")
    public JSON changePassword(@PathVariable("userId") String userId,
                               @Valid @RequestBody UserChangePasswordInDto userChangePasswordInDto){
        authorizeUser(userId);

        userChangePasswordInDto.setUserId(userId);
        return userService.changePassword(userChangePasswordInDto);
    }

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private RequestService requestService;
}
