package com.mcflythekid.lazylearncore.generator;

import com.mcflythekid.lazylearncore.entity.Deck;
import com.mcflythekid.lazylearncore.entity.Vocabdeck;

/**
 * @author McFly the Kid
 */
public class KnowWord extends CardDeckGenerator {

    @Override
    public Integer getVocabType() {
        return VOCAB_TYPE__KNOW_WORD;
    }

    @Override
    public Deck generateDeck(Vocabdeck vocabdeck, Deck deck) {
        deck = super.generateDeck(vocabdeck, deck);
        deck.setName(vocabdeck.getName() + " [Know Word]");
        return deck;
    }
}