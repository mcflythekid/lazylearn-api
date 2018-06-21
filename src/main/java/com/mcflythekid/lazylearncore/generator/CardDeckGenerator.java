package com.mcflythekid.lazylearncore.generator;

import com.mcflythekid.lazylearncore.entity.Card;
import com.mcflythekid.lazylearncore.entity.Deck;
import com.mcflythekid.lazylearncore.entity.Vocab;
import com.mcflythekid.lazylearncore.entity.Vocabdeck;

import java.util.Arrays;
import java.util.List;

/**
 * @author McFly the Kid
 */
public abstract class CardDeckGenerator {

    public static List<CardDeckGenerator> getGenerators(){
        return Arrays.asList(new KnowImage(), new KnowWord(), new Speakable(), new Writeable());
    }

    public static final Integer VOCAB_TYPE__KNOW_IMAGE = 1;
    public static final Integer VOCAB_TYPE__KNOW_WORD = 2;
    public static final Integer VOCAB_TYPE__SPEAKABLE = 3;
    public static final Integer VOCAB_TYPE__WRITEABLE = 4;

    public abstract Integer getVocabType();

    public Deck generateDeck(Vocabdeck vocabdeck, Deck deck){
        if (deck == null){
            deck = new Deck();
            deck.setUserId(vocabdeck.getUserId());
        }
        deck.setArchived(vocabdeck.getArchived());
        deck.setVocabdeckId(vocabdeck.getId());
        deck.setVocabType(getVocabType());
        return deck;
    }

    public Card generateCard(Vocab vocab, Card card, String deckId){
        if (card == null){
            card = new Card();
            card.setUserId(vocab.getUserId());
            card.setDeckId(deckId);
        }
        card.setVocabId(vocab.getId());
        return card;
    }
}
