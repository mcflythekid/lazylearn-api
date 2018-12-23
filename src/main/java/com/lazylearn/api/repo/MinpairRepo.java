package com.lazylearn.api.repo;

import com.lazylearn.api.entity.Minpair;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author McFly the Kid
 */
@Repository
public interface MinpairRepo extends JpaRepository<Minpair, String> {

    @Query("SELECT m FROM Minpair m WHERE " +
            "(" +
            " LOWER(m.word1) LIKE LOWER(CONCAT('%', ?1, '%'))" +
            " OR LOWER(m.word2) LIKE LOWER(CONCAT('%', ?1, '%'))" +
            " OR LOWER(m.language) LIKE LOWER(CONCAT('%', ?1, '%'))" +
            ") AND m.user.id = ?2")
    List<Minpair> findAllByKeywordAndUserId(String keyword, String userId, Pageable pageable);

    @Query("SELECT COUNT(m) FROM Minpair m WHERE " +
            " (" +
            " LOWER(m.word1) LIKE LOWER(CONCAT('%', ?1, '%')) " +
            " OR LOWER(m.word2) LIKE LOWER(CONCAT('%', ?1, '%'))" +
            " OR LOWER(m.language) LIKE LOWER(CONCAT('%', ?1, '%'))" +
            ") AND m.user.id = ?2")
    Long countByKeywordAndUserId(String keyword, String userId);

    @Query("SELECT m FROM Minpair m WHERE " +
            "(" +
            " LOWER(m.word1) LIKE LOWER(CONCAT('%', ?1, '%'))" +
            " OR LOWER(m.word2) LIKE LOWER(CONCAT('%', ?1, '%'))" +
            " OR LOWER(m.language) LIKE LOWER(CONCAT('%', ?1, '%'))" +
            ")")
    List<Minpair> findAllByKeyword(String keyword, Pageable pageable);

    @Query("SELECT COUNT(m) FROM Minpair m WHERE " +
            " (" +
            " LOWER(m.word1) LIKE LOWER(CONCAT('%', ?1, '%')) " +
            " OR LOWER(m.word2) LIKE LOWER(CONCAT('%', ?1, '%'))" +
            " OR LOWER(m.language) LIKE LOWER(CONCAT('%', ?1, '%'))" +
            ")")
    Long countByKeyword(String keyword);
}
