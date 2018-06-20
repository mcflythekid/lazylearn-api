package com.mcflythekid.lazylearncore.indto.vocabdeck;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author McFly the Kid
 */
public class VocabdeckRenameIn {

    @NotBlank
    private String vocabdeckId;

    @NotBlank
    private String newName;

    public String getVocabdeckId() {
        return vocabdeckId;
    }

    public void setVocabdeckId(String vocabdeckId) {
        this.vocabdeckId = vocabdeckId;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
