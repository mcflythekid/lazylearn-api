package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.Utils;
import com.mcflythekid.lazylearncore.entity.User;
import com.mcflythekid.lazylearncore.exception.AppException;
import com.mcflythekid.lazylearncore.indto.AuthLoginInDto;
import com.mcflythekid.lazylearncore.outdto.AuthLoginOutDto;
import com.mcflythekid.lazylearncore.outdto.OAuthOutDto;
import com.mcflythekid.lazylearncore.repo.UserRepo;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author McFly the Kid
 */
@Service
public class AuthService {

    @Value("${security.jwt.client-id}")
    private String clientId;

    @Value("${security.jwt.client-secret}")
    private String clientSecret;

    @Value("${app.oauth-token-checker}")
    private String appOAuthTokenChecker;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    /**
     * @return 32 chars string
     */
    public String getRamdomId(){
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String pre = sdf.format(new Date());
        return pre + Utils.randomString(30);
    }

    public boolean isPasswordValid(String password, User user){
        return passwordEncoder.matches(password, user.getHashedPassword());
    }

    public String hashPassword(String password){
        return passwordEncoder.encode(password);
    }

    public AuthLoginOutDto login(AuthLoginInDto authLoginInDto){
        try {
            User user = userRepo.findByEmail(authLoginInDto.getEmail());

            MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
            body.add("grant_type", "password");
            body.add("username", authLoginInDto.getEmail());
            body.add("password", authLoginInDto.getPassword());
            HttpEntity<?> request = new HttpEntity<>(body, getLoginHeaders());
            OAuthOutDto oAuthOutDto = new RestTemplate().postForObject(appOAuthTokenChecker, request, OAuthOutDto.class);
            return new AuthLoginOutDto(oAuthOutDto.getAccessToken(), user.getId(), user.getEmail());
        } catch (Exception e){
            e.printStackTrace();
            throw new AppException("Wrong email/password", e);
        }
    }

    public String getCurrentUserId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
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


}
