package com.lazylearn.api.repo;

import com.lazylearn.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author McFly the Kid
 */
@Repository
public interface UserRepo extends JpaRepository<User, String> {
    User findByEmail(String email);

    User findByFacebookId(String facebookId);
}
