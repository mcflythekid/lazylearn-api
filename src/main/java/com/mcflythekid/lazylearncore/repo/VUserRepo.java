package com.mcflythekid.lazylearncore.repo;

import com.mcflythekid.lazylearncore.entity.DetailedUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author McFly the Kid
 */
@Repository
public interface VUserRepo extends JpaRepository<DetailedUser, String> {
    //@Query("SELECT new User(u.id, u.email, u.createdOn, u.updatedOn, u.registerIpAddress) " +
    @Query("SELECT u " +
            "FROM DetailedUser u WHERE u.email IS NULL OR (LOWER(u.email) LIKE LOWER(CONCAT('%', ?1, '%')))")
    List<DetailedUser> findAllByEmail(String email, Pageable pageable);

    @Query("SELECT COUNT(u) FROM DetailedUser u WHERE (LOWER(u.email) LIKE LOWER(CONCAT('%', ?1, '%')))")
    Long countAllByEmail(String email);
}
