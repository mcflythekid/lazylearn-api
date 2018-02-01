package com.mcflythekid.lazylearncore.repo;

import com.mcflythekid.lazylearncore.entity.Card;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author McFly the Kid
 */
@Repository
public interface CardRepo extends JpaRepository<Card, String> {

    @Query("SELECT c FROM Card c WHERE c.deckId = :deckId " +
            " AND (c.front LIKE %:search% OR c.back LIKE %:search%)")
    List<Card> findAllByDeckIdAndSearch(@Param("deckId") String deckId, @Param("search") String search,
                                        Pageable pageable);

    @Query("SELECT COUNT(c) FROM Card c WHERE c.deckId = :deckId " +
            " AND (c.front LIKE %:search% OR c.back LIKE %:search%)")
    Long countByDeckIdAndSearch(@Param("deckId") String deckId, @Param("search") String search);

    @Modifying
    @Query("DELETE FROM Card c WHERE c.deckId = :deckId")
    void deleteAllByDeckId(@Param("deckId") String deckId);
}
