package com.mcflythekid.lazylearncore.repo;

import com.mcflythekid.lazylearncore.entity.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author McFly the Kid
 */
@Repository
public interface DeckRepo extends JpaRepository<Deck, String> {
}
