package com.lazylearn.api.indto.card;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author McFly the Kid
 */
public class CardCreateIn {

    @NotBlank
    private String front;

    @NotBlank
    private String back;

    @NotBlank
    private String deckId;

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getDeckId() {
        return deckId;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    public String getFront() {

        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }
}
