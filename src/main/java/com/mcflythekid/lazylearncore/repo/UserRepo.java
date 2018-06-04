package com.mcflythekid.lazylearncore.repo;

import com.mcflythekid.lazylearncore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author McFly the Kid
 */
@Repository
public interface UserRepo extends JpaRepository<User, String>{
    User findByEmail(String email);

    /**
     * @author McFly the Kid
     * @JsonFormat(pattern = "", timezone = "")
     */
}
