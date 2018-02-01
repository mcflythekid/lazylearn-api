package com.mcflythekid.lazylearncore.outdto;

import com.mcflythekid.lazylearncore.entity.Card;
import com.mcflythekid.lazylearncore.entity.Deck;

import java.util.List;

/**
 * @author McFly the Kid
 */
public class LearnOutDto {

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
