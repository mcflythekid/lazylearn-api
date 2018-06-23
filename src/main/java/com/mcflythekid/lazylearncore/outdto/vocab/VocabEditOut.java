package com.mcflythekid.lazylearncore.outdto.vocab;

import com.mcflythekid.lazylearncore.entity.Card;
import com.mcflythekid.lazylearncore.entity.Vocab;

import java.util.List;
import java.util.Map;

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
