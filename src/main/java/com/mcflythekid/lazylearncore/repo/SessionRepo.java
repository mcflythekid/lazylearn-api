package com.mcflythekid.lazylearncore.repo;

import com.mcflythekid.lazylearncore.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author McFly the Kid
 */
@Repository
public interface SessionRepo extends JpaRepository<Session, String> {
    List<Session> findAllByUserIdOrderByCreatedDateDesc(String userId);

    @Transactional(rollbackFor = Exception.class)
    void deleteAllByUserId(String userId);
}
