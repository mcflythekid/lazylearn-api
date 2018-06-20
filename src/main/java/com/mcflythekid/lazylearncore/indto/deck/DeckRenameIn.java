package com.mcflythekid.lazylearncore.indto.deck;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author McFly the Kid
 */
public class DeckRenameIn {

    @NotBlank
    private String deckId;

    @NotBlank
    private String newName;

    public String getDeckId() {
        return deckId;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
