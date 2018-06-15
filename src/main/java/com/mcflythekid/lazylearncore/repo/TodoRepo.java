package com.mcflythekid.lazylearncore.repo;

import com.mcflythekid.lazylearncore.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author McFly the Kid
 */
@Repository
public interface TodoRepo extends JpaRepository<Todo, String> {
}
