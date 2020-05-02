package com.lazylearn.api.repo;

import com.lazylearn.api.entity.MinpairFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author McFly the Kid
 */
@Repository
public interface MinpairFileRepo extends JpaRepository<MinpairFile, String> {
    List<MinpairFile> findAllByMinpairId(String minpairId);

    List<MinpairFile> findAllByMinpairIdAndSide(String minpairId, int side);

    @Query(nativeQuery = true, value = "delete from minpair_file where minpair_id = ?1")
    @Modifying
    void deleteAllByMinpairId(String minpairId);

    @Query(nativeQuery = true, value = "delete from minpair_file where userid = ?1")
    @Modifying
    void deleteAllByUserId(String userId);
}
