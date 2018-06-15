package com.mcflythekid.lazylearncore.repo;

import com.mcflythekid.lazylearncore.entity.Vocab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author McFly the Kid
 */
@Repository
public interface VocabRepo extends JpaRepository<Vocab, String> {
}
