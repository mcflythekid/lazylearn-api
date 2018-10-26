package com.mcflythekid.lazylearncore.indto.card;

import com.mcflythekid.lazylearncore.indto.SearchIn;
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
