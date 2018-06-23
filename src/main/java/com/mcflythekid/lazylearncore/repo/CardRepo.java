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

    Long countAllByDeckIdAndStepAndWakeupOnBeforeAndArchived(String deckId, Integer step, Date wakeupOn, Integer archived);
    Long countAllByUserIdAndStepAndWakeupOnBeforeAndArchived(String userId, Integer step, Date wakeupOn, Integer archived);

    Long countAllByDeckIdAndStepAndWakeupOnAfterAndArchived(String deckId, Integer step, Date wakeupOn, Integer archived);
    Long countAllByUserIdAndStepAndWakeupOnAfterAndArchived(String userId, Integer step, Date wakeupOn, Integer archived);

    Long countAllByDeckIdAndStepAndArchived(String deckId, Integer step, Integer archived);
    Long countAllByUserIdAndStepAndArchived(String userId, Integer step, Integer archived);

    @Modifying
    @Query("UPDATE Card c SET c.archived = 1 WHERE c.deckId = :deckId")
    void archiveAllByDeckId(@Param("deckId") String deckId);

    @Modifying
    @Query("UPDATE Card c SET c.archived = 0 WHERE c.deckId = :deckId")
    void unarchiveAllByDeckId(@Param("deckId") String deckId);

    @Modifying
    @Query("UPDATE Card c SET c.archived = ?1 WHERE c.deckId = ?2")
    void setArchivedAllByDeckId(Integer archived, String deckId);

    @Modifying
    @Query("DELETE FROM Card c WHERE c.vocabId = :vocabId")
    void deleteAllByVocabId(@Param("vocabId") String vocabId);

    Card findByDeckIdAndVocabId(String deckId, String vocabId);

    List<Card> findAllByVocabId(String vocabId);
}
