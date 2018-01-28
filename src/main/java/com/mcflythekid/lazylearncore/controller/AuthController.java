package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.indto.AuthLoginInDto;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author McFly the Kid
 */
@RestController
public class AuthController {

    @PostMapping("/login")
    public String login(@Valid @RequestBody AuthLoginInDto authLoginInDto){
        return authService.login(authLoginInDto);
    }

    @Autowired
    private AuthService authService;
}
