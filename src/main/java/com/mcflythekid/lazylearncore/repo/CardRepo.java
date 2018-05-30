package com.mcflythekid.lazylearncore.repo;

import com.mcflythekid.lazylearncore.entity.Card;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author McFly the Kid
 */
@Repository
public interface CardRepo extends JpaRepository<Card, String> {

    @Query("SELECT c FROM Card c WHERE c.deckId = :deckId AND" +
            " (LOWER(c.front) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(c.back) LIKE LOWER(CONCAT('%', :search, '%')))")
    List<Card> findAllByDeckIdAndSearch(@Param("deckId") String deckId, @Param("search") String search,
                                        Pageable pageable);

    @Query("SELECT COUNT(c) FROM Card c WHERE c.deckId = :deckId AND" +
            " (LOWER(c.front) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(c.back) LIKE LOWER(CONCAT('%', :search, '%')))")
    Long countByDeckIdAndSearch(@Param("deckId") String deckId, @Param("search") String search);

    @Modifying
    @Query("DELETE FROM Card c WHERE c.deckId = :deckId")
    void deleteAllByDeckId(@Param("deckId") String deckId);

    List<Card> findAllByDeckId(String deckId);

    List<Card> findAllByDeckIdAndWakeupOnBefore(String deckId, Date wakeupOn);

    Long countAllByDeckIdAndStepAndWakeupOnBefore(String deckId, Integer step, Date wakeupOn);
    Long countAllByUserIdAndStepAndWakeupOnBefore(String userId, Integer step, Date wakeupOn);

    Long countAllByDeckIdAndStepAndWakeupOnAfter(String deckId, Integer step, Date wakeupOn);
    Long countAllByUserIdAndStepAndWakeupOnAfter(String userId, Integer step, Date wakeupOn);

    Long countAllByDeckIdAndStep(String deckId, Integer step);
    Long countAllByUserIdAndStep(String userId, Integer step);

    @Modifying
    @Query("UPDATE Card c SET c.archived = 1 WHERE c.deckId = :deckId")
    void archiveAllByDeckId(@Param("deckId") String deckId);

    @Modifying
    @Query("UPDATE Card c SET c.archived = 0 WHERE c.deckId = :deckId")
    void unarchiveAllByDeckId(@Param("deckId") String deckId);
}
