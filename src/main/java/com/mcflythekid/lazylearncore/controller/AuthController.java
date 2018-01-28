package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.indto.AuthLoginInDto;
import com.mcflythekid.lazylearncore.outdto.AuthLoginOutDto;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author McFly the Kid
 */
@RestController
public class AuthController {

    @PostMapping("/login")
    public AuthLoginOutDto login(@Valid @RequestBody AuthLoginInDto authLoginInDto){
        return authService.login(authLoginInDto);
    }

    @GetMapping("/ping")
    public JSON ping(){
        return JSON.ok();
    }

    @Autowired
    private AuthService authService;
}
