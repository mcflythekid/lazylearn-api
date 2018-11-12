package com.lazylearn.api.indto.card;

import org.hibernate.validator.constraints.NotBlank;

public class CardChangeDeckIn {

    @NotBlank
    private String cardId;

    @NotBlank
    private String deckId;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getDeckId() {
        return deckId;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }
}
