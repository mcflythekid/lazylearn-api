package com.mcflythekid.lazylearncore.repo;

import com.mcflythekid.lazylearncore.entity.Deck;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author McFly the Kid
 */
@Repository
public interface DeckRepo extends JpaRepository<Deck, String> {
    List<Deck> findAllByUserIdAndNameContaining(String userId, String name, Pageable pageable);
    Long countByUserIdAndNameContaining(String userId, String name);
}
