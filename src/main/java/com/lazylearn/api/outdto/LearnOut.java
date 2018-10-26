package com.lazylearn.api.outdto;

import com.lazylearn.api.entity.Card;
import com.lazylearn.api.entity.Deck;

import java.util.List;

/**
 * @author McFly the Kid
 */
public class LearnOut {

    private Deck deck;
    private List<Card> cards;

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
