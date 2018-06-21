package com.mcflythekid.lazylearncore.deckgenerator;

import com.mcflythekid.lazylearncore.entity.Deck;
import com.mcflythekid.lazylearncore.entity.Vocabdeck;

/**
 * @author McFly the Kid
 */
public class KnowImage extends DeckGenerator {

    @Override
    public Integer getVocabType() {
        return VOCAB_TYPE__KNOW_IMAGE;
    }

    @Override
    public Deck generate(Vocabdeck vocabdeck, Deck deck) {
        deck = super.generate(vocabdeck, deck);
        deck.setName(vocabdeck.getName() + " [Know Image]");
        return deck;
    }
}