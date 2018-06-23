package com.mcflythekid.lazylearncore.generator;

import com.mcflythekid.lazylearncore.entity.Card;
import com.mcflythekid.lazylearncore.entity.Deck;
import com.mcflythekid.lazylearncore.entity.Vocab;
import com.mcflythekid.lazylearncore.entity.Vocabdeck;
import com.mcflythekid.lazylearncore.util.ResouresUtils;

import java.io.IOException;

/**
 * @author McFly the Kid
 */
public class KnowImage extends CardDeckGenerator {

    @Override
    public Integer getVocabType() {
        return VOCAB_TYPE__KNOW_IMAGE;
    }

    @Override
    public Deck generateDeck(Vocabdeck vocabdeck, Deck deck) {
        deck = super.generateDeck(vocabdeck, deck);
        deck.setName(vocabdeck.getName() + " [Know Image]");
        return deck;
    }

    @Override
    public Card generateCard(Vocab vocab, Card card, String deckId) throws Exception {
        card = super.generateCard(vocab, card, deckId);
        String frontTemplate = ResouresUtils.read("/template/knowImage-front.html");
        String backTemplate = ResouresUtils.read("/template/knowImage-back.html");
        card.setFront(frontTemplate);
        card.setBack(backTemplate);
        return card;
    }
}