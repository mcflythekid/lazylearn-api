package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.entity.User;
import com.mcflythekid.lazylearncore.indto.DeckCreateInDto;
import com.mcflythekid.lazylearncore.indto.UserResetPasswordInDto;
import com.mcflythekid.lazylearncore.indto.UserChangePasswordInDto;
import com.mcflythekid.lazylearncore.indto.UserRegisterInDto;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.outdto.UserRegisterOutDto;
import com.mcflythekid.lazylearncore.service.AuthService;
import com.mcflythekid.lazylearncore.service.DeckService;
import com.mcflythekid.lazylearncore.service.RequestService;
import com.mcflythekid.lazylearncore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.ShellProperties;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author McFly the Kid
 */
@RestController
public class UserController {

    @PostMapping("/user")
    public UserRegisterOutDto register(@Valid @RequestBody UserRegisterInDto userRegisterInDto){
        userRegisterInDto.setRegisterIpAddress(requestService.getIpAddress());
        return userService.register(userRegisterInDto);
    }

    @PutMapping("/user/{userId}/password")
    public JSON changePassword(@PathVariable("userId") String userId,
                               @Valid @RequestBody UserChangePasswordInDto userChangePasswordInDto){
        User user = userService.findOne(userId);
        authService.checkOwner(user);
        return userService.changePassword(userChangePasswordInDto, user);
    }

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private RequestService requestService;
}
