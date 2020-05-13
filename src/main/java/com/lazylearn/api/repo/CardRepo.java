package com.lazylearn.api.repo;

import com.lazylearn.api.entity.Card;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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

    @Modifying
    @Query("DELETE FROM Card c WHERE c.minpairId = :minpairId")
    void deleteAllByMinpairId(@Param("minpairId") String minpairId);

    List<Card> findAllByDeckIdAndArchived(String deckId, Integer archived);

    List<Card> findAllByDeckId(String deckId);

    List<Card> findAllByDeckIdAndArchivedAndWakeupOnBefore(String deckId, Integer archived, Date wakeupOn);


    @Query(nativeQuery = true, value =
            " SELECT * FROM card c " +
            " JOIN deck d ON d.id = c.deckid AND d.type IN ('default', 'vocab')" +
            " WHERE " +
            "     c.userid = ?1" +
            " AND c.archived = 0" +
            " AND c.wakeupOn < ?2" +
            " ")
    List<Card> findAllByUserIdAndWakeupOnBefore(String userId, Date wakeupOn);

    @Query(nativeQuery = true, value =
            " SELECT * FROM card c " +
                    " JOIN deck d ON d.id = c.deckid AND d.type IN ('default', 'vocab')" +
                    " WHERE " +
                    "     c.userid = ?1" +
                    " AND c.archived = 0" +
                    " AND c.wakeupOn < ?2" +
                    " AND (c.learnedOn < ?3 OR c.learnedOn IS NULL)" +
                    " ")
    List<Card> findAllByUserIdAndWakeupOnBeforeAndLearnedOnBefore(String userId, Date wakeupOn, Date midNight);

    @Query(nativeQuery = true, value =
            " SELECT count(*) FROM card c " +
                    " JOIN deck d ON d.id = c.deckid AND d.type IN ('default', 'vocab')" +
                    " WHERE " +
                    "     c.userid = ?1" +
                    " AND c.archived = 0" +
                    " AND c.wakeupOn < ?2" +
                    " ")
    long countAllByUserIdAndWakeupOnBefore(String userId, Date wakeupOn);


    @Query(nativeQuery = true, value =
            " SELECT count(*) FROM card c " +
                    " JOIN deck d ON d.id = c.deckid AND d.type IN ('default', 'vocab')" +
                    " WHERE " +
                    "     c.userid = ?1" +
                    " AND c.archived = 0" +
                    " AND c.wakeupOn < ?2" +
                    " AND (c.learnedOn < ?3 OR c.learnedOn IS NULL)" +
                    " ")
    long countAllByUserIdAndWakeupOnBeforeAndLearnedOnBefore(String userId, Date wakeupOn, Date midNight);

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

    Card findByDeckIdAndFront(String deckId, String front);

    Long countAllByDeckId(String deckId);

    Card findByMinpairId(String minpairId);
}
