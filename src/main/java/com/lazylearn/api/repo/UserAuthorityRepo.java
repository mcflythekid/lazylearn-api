package com.lazylearn.api.repo;

import com.lazylearn.api.entity.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author McFly the Kid
 */
@Repository
public interface UserAuthorityRepo extends JpaRepository<UserAuthority, String> {
    List<UserAuthority> findAllByUserId(String userId);
}
