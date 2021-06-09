package com.lazylearn.api.service;

import com.lazylearn.api.entity.UserAuthority;
import com.lazylearn.api.repo.UserAuthorityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

/**
 * @author McFly the Kid
 */
@Service
public class AuthorityService {

    @Autowired
    private UserAuthorityRepo userAuthorityRepo;

    @Transactional(rollbackFor = Exception.class)
    public void createAuthority(String userId, String authority) {
        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setUserId(userId);
        userAuthority.setAuthority(authority);
        userAuthorityRepo.save(userAuthority);
    }

    public String getUserAuthorities(String userId) {
        return userAuthorityRepo.findAllByUserId(userId)
                .stream().map(UserAuthority::getAuthority).collect(Collectors.joining(","));
    }
}
