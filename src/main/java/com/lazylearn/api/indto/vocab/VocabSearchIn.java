package com.lazylearn.api.indto.vocab;

import com.lazylearn.api.indto.SearchIn;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author McFly the Kid
 */
public class VocabSearchIn extends SearchIn {

    @NotBlank
    private String vocabdeckId;

    public String getVocabdeckId() {
        return vocabdeckId;
    }

    public void setVocabdeckId(String vocabdeckId) {
        this.vocabdeckId = vocabdeckId;
    }
}
