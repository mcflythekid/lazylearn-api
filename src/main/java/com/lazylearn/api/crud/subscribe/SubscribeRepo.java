package com.lazylearn.api.crud.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 4nha
 * Date: 2020-05-01
 */
@Repository
public interface SubscribeRepo extends JpaRepository<SubscribeEntity, String> {
}
