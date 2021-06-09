package com.lazylearn.api.repo;

import com.lazylearn.api.entity.Article;
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
            " LOWER(m.name) LIKE LOWER(CONCAT('%', ?1, '%'))" +
            " OR LOWER(m.content) LIKE LOWER(CONCAT('%', ?1, '%'))" +
            ") AND m.user.id = ?2")
    List<Article> findAllByKeywordAndUserId(String keyword, String userId, Pageable pageable);

    @Query("SELECT COUNT(m) FROM Article m WHERE " +
            " (" +
            " LOWER(m.name) LIKE LOWER(CONCAT('%', ?1, '%')) " +
            " OR LOWER(m.content) LIKE LOWER(CONCAT('%', ?1, '%'))" +
            ")  AND m.user.id = ?2")
    Long countByKeywordAndUserId(String keyword, String userId);

    @Query("SELECT m FROM Article m WHERE " +
            "(" +
            " LOWER(m.name) LIKE LOWER(CONCAT('%', ?1, '%'))" +
            " OR LOWER(m.content) LIKE LOWER(CONCAT('%', ?1, '%'))" +
            " OR LOWER(m.url) LIKE LOWER(CONCAT('%', ?1, '%'))" +
            " OR LOWER(m.user.id) LIKE LOWER(CONCAT('%', ?1, '%'))" +
            " OR LOWER(m.user.fullName) LIKE LOWER(CONCAT('%', ?1, '%'))" +
            " OR LOWER(m.user.email) LIKE LOWER(CONCAT('%', ?1, '%'))" +
            ")")
    List<Article> findAllByKeyword(String keyword, Pageable pageable);

    @Query("SELECT COUNT(m) FROM Article m WHERE " +
            " (" +
            " LOWER(m.name) LIKE LOWER(CONCAT('%', ?1, '%')) " +
            " OR LOWER(m.content) LIKE LOWER(CONCAT('%', ?1, '%'))" +
            " OR LOWER(m.url) LIKE LOWER(CONCAT('%', ?1, '%'))" +
            " OR LOWER(m.user.id) LIKE LOWER(CONCAT('%', ?1, '%'))" +
            " OR LOWER(m.user.fullName) LIKE LOWER(CONCAT('%', ?1, '%'))" +
            " OR LOWER(m.user.email) LIKE LOWER(CONCAT('%', ?1, '%'))" +
            ")")
    Long countByKeyword(String keyword);

    List<Article> findAllBySlug(String slug);

    @Query(nativeQuery = true, value = "select * FROM article Where slug is not null")
    List<Article> findAllSlugAndName();
}
