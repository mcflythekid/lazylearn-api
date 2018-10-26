package com.lazylearn.api.outdto.vocab;

import com.lazylearn.api.entity.Card;
import com.lazylearn.api.entity.Vocab;

import java.util.List;

/**
 * @author McFly the Kid
 */
public class VocabEditOut {

    private Vocab vocab;
    private List<Card> cards;

    public Vocab getVocab() {
        return vocab;
    }

    public void setVocab(Vocab vocab) {
        this.vocab = vocab;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
