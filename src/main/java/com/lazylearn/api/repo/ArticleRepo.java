package com.lazylearn.api.repo;

import com.lazylearn.api.entity.Article;
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
public interface ArticleRepo extends JpaRepository<Article, String> {

    @Query("SELECT m FROM Article m WHERE " +
            "(" +
            " LOWER(m.name) LIKE LOWER(CONCAT('%', ?2, '%'))" +
            " OR LOWER(m.content) LIKE LOWER(CONCAT('%', ?2, '%'))" +
            " OR LOWER(m.category) LIKE LOWER(CONCAT('%', ?2, '%'))" +
            ") AND m.id NOT IN (SELECT c.front FROM Card c WHERE c.userId = ?1 AND c.articleCategory <> '')")
    List<Article> findAllByUserIdAndSearch(String userId, String search, Pageable pageable);

    @Query("SELECT COUNT(m) FROM Article m WHERE " +
            " (" +
            " LOWER(m.name) LIKE LOWER(CONCAT('%', ?2, '%')) " +
            " OR LOWER(m.content) LIKE LOWER(CONCAT('%', ?2, '%'))" +
            " OR LOWER(m.category) LIKE LOWER(CONCAT('%', ?2, '%'))" +
            ") AND m.id NOT IN (SELECT c.front FROM Card c WHERE c.userId = ?1 AND c.articleCategory <> '')")
    Long countByUserIdAndSearch(String userId, String search);
}
