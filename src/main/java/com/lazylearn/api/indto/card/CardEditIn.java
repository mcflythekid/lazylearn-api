package com.lazylearn.api.indto.card;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author McFly the Kid
 */
public class CardEditIn {

    @NotBlank
    private String front;

    @NotBlank
    private String back;

    @NotBlank
    private String cardId;

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
