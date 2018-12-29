package com.lazylearn.api.repo;

import com.lazylearn.api.entity.Deck;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author McFly the Kid
 */
@Repository
public interface DeckRepo extends JpaRepository<Deck, String> {
    List<Deck> findAllByVocabdeckId(String vocabdeckId);
    Deck findByVocabdeckIdAndVocabType(String vocabdeckId, Integer vocabType);
    Deck findByMinpairLanguageAndUserId(String minpairLanguage, String userId);
    Deck findByTypeAndUserId(String type, String userId);

    List<Deck> findAllByUserIdAndNameContainingIgnoreCase(String userId, String name, Pageable pageable);
    Long countByUserIdAndNameContainingIgnoreCase(String userId, String name);
}
