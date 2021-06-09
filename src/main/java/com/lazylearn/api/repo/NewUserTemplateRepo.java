package com.lazylearn.api.repo;

import com.lazylearn.api.entity.NewUserTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 4nha
 * Date: 2020-05-02
 */
@Repository
public interface NewUserTemplateRepo extends JpaRepository<NewUserTemplate, String> {
}
