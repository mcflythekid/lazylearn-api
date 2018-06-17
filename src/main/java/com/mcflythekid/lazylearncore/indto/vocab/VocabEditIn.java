package com.mcflythekid.lazylearncore.indto.vocab;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author McFly the Kid
 */
public class VocabEditIn {
    @NotBlank
    private String vocabId;

    public String getVocabId() {
        return vocabId;
    }

    public void setVocabId(String vocabId) {
        this.vocabId = vocabId;
    }
}
