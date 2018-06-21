package com.mcflythekid.lazylearncore.deckgenerator;

import com.mcflythekid.lazylearncore.entity.Deck;
import com.mcflythekid.lazylearncore.entity.Vocab;
import com.mcflythekid.lazylearncore.entity.Vocabdeck;

/**
 * @author McFly the Kid
 */
public class Speakable extends DeckGenerator {

    @Override
    public Integer getVocabType() {
        return VOCAB_TYPE__SPEAKABLE;
    }

    @Override
    public Deck generate(Vocabdeck vocabdeck, Deck deck) {
        deck = super.generate(vocabdeck, deck);
        deck.setName(vocabdeck.getName() + " [Speakable]");
        return deck;
    }
}