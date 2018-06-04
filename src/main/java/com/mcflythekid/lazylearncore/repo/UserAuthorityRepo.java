package com.mcflythekid.lazylearncore.repo;

import com.mcflythekid.lazylearncore.entity.UserAuthority;
import com.mcflythekid.lazylearncore.entity.key.UserAuthorityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author McFly the Kid
 */
@Repository
public interface UserAuthorityRepo extends JpaRepository<UserAuthority, UserAuthorityKey> {
    List<UserAuthority> findAllByKeyUserId(String userId);
}
