package com.mcflythekid.lazylearncore.repo;

import com.mcflythekid.lazylearncore.entity.ForgetPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author McFly the Kid
 */
@Repository
public interface ForgetPasswordRepo extends JpaRepository<ForgetPassword, String>{
}
