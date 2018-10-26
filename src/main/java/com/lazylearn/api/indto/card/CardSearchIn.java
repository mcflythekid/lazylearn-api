package com.lazylearn.api.indto.card;

import com.lazylearn.api.indto.SearchIn;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author McFly the Kid
 */
public class CardSearchIn extends SearchIn {

    @NotBlank
    private String deckId;

    public String getDeckId() {
        return deckId;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }
}
