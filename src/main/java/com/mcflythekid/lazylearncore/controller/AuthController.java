package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.indto.AuthLoginInDto;
import com.mcflythekid.lazylearncore.outdto.AuthLoginOutDto;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author McFly the Kid
 */
@RestController
public class AuthController {

    @PostMapping("/ping")
    public JSON ping(){
        return JSON.ok();
    }

    @PostMapping("/login")
    public AuthLoginOutDto login(@Valid @RequestBody AuthLoginInDto authLoginInDto){
        return authService.login(authLoginInDto);
    }

    @PostMapping("/revoke-token")
    public JSON logout(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.contains("Bearer")){
            String tokenId = authorization.substring("Bearer".length()+1);
            tokenServices.revokeToken(tokenId);
        }
        return JSON.ok();
    }

    @Resource(name = "tokenServices")
    private ConsumerTokenServices tokenServices;

    @Autowired
    private AuthService authService;
}
