package com.mcflythekid.lazylearncore.repo;

import com.mcflythekid.lazylearncore.entity.Deck;
import com.mcflythekid.lazylearncore.entity.VDeck;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author McFly the Kid
 */
@Repository
public interface VDeckRepo extends JpaRepository<VDeck, String> {
    List<VDeck> findAllByUserIdAndNameContainingIgnoreCase(String userId, String name, Pageable pageable);
    Long countByUserIdAndNameContainingIgnoreCase(String userId, String name);
}
