package com.lazylearn.api.repo;

import com.lazylearn.api.entity.MinpairFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author McFly the Kid
 */
@Repository
public interface MinpairFileRepo extends JpaRepository<MinpairFile, String> {
    List<MinpairFile> findAllByMinpairId(String minpairId);
}
