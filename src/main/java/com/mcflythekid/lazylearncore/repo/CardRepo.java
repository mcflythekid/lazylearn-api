package com.mcflythekid.lazylearncore.repo;

import com.mcflythekid.lazylearncore.entity.Card;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author McFly the Kid
 */
@Repository
public interface CardRepo extends JpaRepository<Card, String> {
    List<Card> findAllByDeckId(String deckId);
    List<Card> findAllByDeckIdAndFrontContaining(String id, String search, Pageable pageable);
    Long countByDeckIdAndFrontContaining(String id, String search);
}
