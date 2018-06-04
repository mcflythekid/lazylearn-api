package com.mcflythekid.lazylearncore.repo;

import com.mcflythekid.lazylearncore.entity.ExpiredToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author McFly the Kid
 */
@Repository
public interface ExpiredTokenRepo extends JpaRepository<ExpiredToken, String> {
    Long countBySignatureAndExpirationBefore(String token, Date now);
}
