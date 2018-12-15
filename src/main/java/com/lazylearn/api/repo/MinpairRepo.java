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

    @Query("SELECT m FROM Minpair m WHERE m.userId = ?1 AND" +
            "(" +
            " LOWER(m.word1) LIKE LOWER(CONCAT('%', ?2, '%'))" +
            " OR LOWER(m.word2) LIKE LOWER(CONCAT('%', ?2, '%'))" +
            " OR LOWER(m.language) LIKE LOWER(CONCAT('%', ?2, '%'))" +
            ") AND m.id NOT IN (SELECT c.front FROM Card c WHERE c.userId = ?1 AND c.minpairLanguage <> '')")
    List<Minpair> findAllByUserIdAndSearch(String userId, String search, Pageable pageable);

    @Query("SELECT COUNT(m) FROM Minpair m WHERE m.userId = ?1 AND" +
            " (" +
            " LOWER(m.word1) LIKE LOWER(CONCAT('%', ?2, '%')) " +
            " OR LOWER(m.word2) LIKE LOWER(CONCAT('%', ?2, '%'))" +
            " OR LOWER(m.language) LIKE LOWER(CONCAT('%', ?2, '%'))" +
            ") AND m.id NOT IN (SELECT c.front FROM Card c WHERE c.userId = ?1 AND c.minpairLanguage <> '')")
    Long countByUserIdAndSearch(String userId, String search);
}
