package com.lazylearn.api.repo;

import com.lazylearn.api.entity.VocabSaved;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 4nha
 * Date: 2020-05-13
 */
@Repository
public interface VocabSavedRepo extends JpaRepository<VocabSaved, String> {
    VocabSaved findByWordAndLanguageId(String word, int languageId);
}
