package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.entity.User;
import com.mcflythekid.lazylearncore.indto.AuthLoginInDto;
import com.mcflythekid.lazylearncore.outdto.OAuthOutDto;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author McFly the Kid
 */
@Service
public class AuthService {

    /**
     * @return 32 chars string
     */
    public String getRamdomId(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public boolean isPasswordValid(String password, User user){
        return passwordEncoder.isPasswordValid(user.getHashedPassword(), password, null);
    }

    public String hashPassword(String password){
        return passwordEncoder.encodePassword(password, null);
    }

    public String login(AuthLoginInDto authLoginInDto){

        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        body.add("grant_type", "password");
        body.add("username", authLoginInDto.getEmail());
        body.add("password", authLoginInDto.getPassword());
        HttpEntity<?> request = new HttpEntity<>(body, getLoginHeaders());

        OAuthOutDto x = new RestTemplate().postForObject(appOAuthTokenChecker, request, OAuthOutDto.class);
        return x.getAccessToken();
    }

    private HttpHeaders getLoginHeaders(){
        String plainCreds = clientId + ":" + clientSecret;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        return headers;
    }

    @Value("${security.jwt.client-id}")
    private String clientId;

    @Value("${security.jwt.client-secret}")
    private String clientSecret;

    @Value("${app.oauth-token-checker}")
    private String appOAuthTokenChecker;

    @Autowired
    private ShaPasswordEncoder passwordEncoder;
}
