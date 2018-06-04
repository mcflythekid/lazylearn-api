package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.config.jwt.JWTTokenProvider;
import com.mcflythekid.lazylearncore.entity.ExpiredToken;
import com.mcflythekid.lazylearncore.entity.User;
import com.mcflythekid.lazylearncore.config.exception.AppException;
import com.mcflythekid.lazylearncore.indto.AuthLoginInDto;
import com.mcflythekid.lazylearncore.outdto.AuthLoginOutDto;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.repo.ExpiredTokenRepo;
import com.mcflythekid.lazylearncore.repo.UserRepo;
import com.mcflythekid.lazylearncore.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * @author McFly the Kid
 */
@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ExpiredTokenRepo expiredTokenRepo;
    @Autowired
    private JWTTokenProvider JWTTokenProvider;

    public AuthLoginOutDto login(AuthLoginInDto authLoginInDto){
        User user = userRepo.findByEmail(authLoginInDto.getEmail());
        if (user == null){
            throw new AppException("Email not found");
        }
        if (!passwordEncoder.matches(authLoginInDto.getPassword(), user.getHashedPassword())){
            throw new AppException("Wrong password");
        }
        String token = JWTTokenProvider.createToken(user);
        return new AuthLoginOutDto(token, user.getId(), user.getEmail());
    }

    @Transactional(rollbackFor = Exception.class)
    public JSON logout() {
        SecurityUtils.getCurrentUserJWT().ifPresent(jwtToken->{
            expiredTokenRepo.save(new ExpiredToken(jwtToken));
        });
        return JSON.ok();
    }
}