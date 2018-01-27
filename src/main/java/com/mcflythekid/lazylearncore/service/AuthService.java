package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

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

    @Autowired
    private ShaPasswordEncoder passwordEncoder;
}
