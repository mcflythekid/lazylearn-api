package com.lazylearn.api.repo;

import com.lazylearn.api.entity.Vocab;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author McFly the Kid
 */
@Repository
public interface VocabRepo extends JpaRepository<Vocab, String> {

    List<Vocab> findAllByVocabdeckId(String vocabdeckId);

    @Query("SELECT v FROM Vocab v WHERE v.vocabdeckId = ?1 AND" +
            " (LOWER(v.word) LIKE LOWER(CONCAT('%', ?2, '%')) " +
            "OR LOWER(v.phonetic) LIKE LOWER(CONCAT('%', ?2, '%'))" +
            "OR LOWER(v.phrase) LIKE LOWER(CONCAT('%', ?2, '%'))" +
            "OR LOWER(v.personalConnection) LIKE LOWER(CONCAT('%', ?2, '%'))" +
            ")")
    List<Vocab> findAllByVocabdeckIdAndSearch(String vocabdeckId, String search, Pageable pageable);

    @Query("SELECT COUNT(v) FROM Vocab v WHERE v.vocabdeckId = ?1 AND" +
            " (LOWER(v.word) LIKE LOWER(CONCAT('%', ?2, '%')) " +
            "OR LOWER(v.phonetic) LIKE LOWER(CONCAT('%', ?2, '%'))" +
            "OR LOWER(v.phrase) LIKE LOWER(CONCAT('%', ?2, '%'))" +
            "OR LOWER(v.personalConnection) LIKE LOWER(CONCAT('%', ?2, '%'))" +
            ")")
    Long countByVocabdeckIdAndSearch(String vocabdeckId, String search);

    Vocab findByWordAndUserId(String word, String userId);

    Vocab findByIdNotAndWordAndUserId(String restrictedId, String word, String userId);
}
