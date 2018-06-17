package com.mcflythekid.lazylearncore.repo;

import com.mcflythekid.lazylearncore.entity.Card;
import com.mcflythekid.lazylearncore.entity.DetailedDeck;
import com.mcflythekid.lazylearncore.entity.Minpair;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author McFly the Kid
 */
@Repository
public interface MinpairRepo extends JpaRepository<Minpair, String> {

    @Query("SELECT m FROM Minpair m WHERE m.userId = ?1 AND" +
            " (LOWER(m.word1) LIKE LOWER(CONCAT('%', ?2, '%')) OR LOWER(m.word2) LIKE LOWER(CONCAT('%', ?2, '%')))")
    List<Minpair> findAllByUserIdAndSearch(String deckId, String search, Pageable pageable);

    @Query("SELECT COUNT(m) FROM Minpair m WHERE m.userId = ?1 AND" +
            " (LOWER(m.word1) LIKE LOWER(CONCAT('%', ?2, '%')) OR LOWER(m.word2) LIKE LOWER(CONCAT('%', ?2, '%')))")
    Long countByUserIdAndSearch(String deckId, String search);
}
