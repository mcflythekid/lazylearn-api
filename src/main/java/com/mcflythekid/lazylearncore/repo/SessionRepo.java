package com.mcflythekid.lazylearncore.repo;

import com.mcflythekid.lazylearncore.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author McFly the Kid
 */
@Repository
public interface SessionRepo extends JpaRepository<Session, String> {
}
