package com.mcflythekid.lazylearncore.generator;

import com.mcflythekid.lazylearncore.entity.Card;
import com.mcflythekid.lazylearncore.entity.Deck;
import com.mcflythekid.lazylearncore.entity.Vocab;
import com.mcflythekid.lazylearncore.entity.Vocabdeck;
import org.apache.commons.text.StringSubstitutor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author McFly the Kid
 */
public abstract class CardDeckGenerator {

    private String fileRoot;

    protected CardDeckGenerator(String fileRoot) {
        this.fileRoot = fileRoot;
    }

    public static List<CardDeckGenerator> getGenerators(String fileRoot){
        return Arrays.asList(new KnowImage(fileRoot), new KnowWord(fileRoot), new Speakable(fileRoot), new Writeable(fileRoot));
    }

    public static final Integer VOCAB_TYPE__KNOW_IMAGE = 1;
    public static final Integer VOCAB_TYPE__KNOW_WORD = 2;
    public static final Integer VOCAB_TYPE__SPEAKABLE = 3;
    public static final Integer VOCAB_TYPE__WRITEABLE = 4;

    public abstract String getPostfix();
    public abstract Integer getVocabType();
    public abstract String getCardFrontTemplate() throws Exception;
    public abstract String getCardBackTemplate() throws Exception;

    public Deck generateDeck(Vocabdeck vocabdeck, Deck deck){
        if (deck == null){
            deck = new Deck();
            deck.setUserId(vocabdeck.getUserId());
        }
        deck.setArchived(vocabdeck.getArchived());
        deck.setVocabdeckId(vocabdeck.getId());
        deck.setVocabType(getVocabType());
        deck.setName(deck.getName() + " " + getPostfix());
        return deck;
    }

    public Card generateCard(Vocab vocab, Card card, String deckId) throws Exception {
        if (card == null){
            card = new Card();
            card.setUserId(vocab.getUserId());
            card.setDeckId(deckId);
        }
        card.setVocabId(vocab.getId());
        card.setFront(formatCardContent(getCardFrontTemplate(), vocab));
        card.setBack(formatCardContent(getCardBackTemplate(), vocab));
        return card;
    }

    /**
     *
     * @param template   "The ${animal} jumped over the ${target}."
     * @param vocab
     * @return
     */
    public String formatCardContent(String template, Vocab vocab){
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("id", vocab.getId());
        valuesMap.put("word", vocab.getWord());
        valuesMap.put("phonetic", vocab.getPhonetic());
        valuesMap.put("gender", vocab.getGender());
        valuesMap.put("personalConnection", vocab.getPersonalConnection());
        valuesMap.put("audioPath", fileRoot + vocab.getAudioPath());
        valuesMap.put("imagePath", fileRoot + vocab.getImagePath());
        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        return sub.replace(template);
    }
}