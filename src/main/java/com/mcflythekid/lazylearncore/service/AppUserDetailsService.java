package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.Const;
import com.mcflythekid.lazylearncore.entity.User;
import com.mcflythekid.lazylearncore.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author McFly the Kid
 */
@Component
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);

        if(user == null) {
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", email));
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(Const.ROLE_DEFAULT));

        UserDetails userDetails = new org.springframework.security.core.userdetails.
                User(user.getEmail(), user.getHashedPassword(), authorities);

        return userDetails;
    }
}
