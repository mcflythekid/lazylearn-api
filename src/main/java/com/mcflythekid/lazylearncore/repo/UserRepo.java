package com.mcflythekid.lazylearncore.repo;

import com.mcflythekid.lazylearncore.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author McFly the Kid
 */
@Repository
public interface UserRepo extends JpaRepository<User, String>{
    User findByEmail(String email);
}
