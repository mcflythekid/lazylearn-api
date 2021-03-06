package com.lazylearn.api.repo;

import com.lazylearn.api.entity.Reset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author McFly the Kid
 */
@Repository
public interface ForgetPasswordRepo extends JpaRepository<Reset, String> {

    @Modifying
    @Transactional(rollbackOn = Exception.class)
    void deleteByUserId(String userId);
}
