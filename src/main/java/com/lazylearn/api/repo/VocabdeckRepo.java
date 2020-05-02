package com.lazylearn.api.repo;

import com.lazylearn.api.entity.Vocabdeck;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author McFly the Kid
 */
@Repository
public interface VocabdeckRepo extends JpaRepository<Vocabdeck, String> {
    List<Vocabdeck> findAllByUserIdAndNameContainingIgnoreCase(String userId, String name, Pageable pageable);
    Long countByUserIdAndNameContainingIgnoreCase(String userId, String name);

    Long countByUserIdAndCloneableid(String userId, String cloneableId);
}
