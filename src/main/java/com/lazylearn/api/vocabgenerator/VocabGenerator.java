package com.lazylearn.api.vocabgenerator;

import com.lazylearn.api.entity.Card;
import com.lazylearn.api.entity.Deck;
import com.lazylearn.api.entity.Vocab;
import com.lazylearn.api.entity.Vocabdeck;
import org.apache.commons.text.StringSubstitutor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author McFly the Kid
 */
public abstract class VocabGenerator {

    private String fileRoot;

    protected VocabGenerator(String fileRoot) {
        this.fileRoot = fileRoot;
    }

    public static List<VocabGenerator> getGenerators(String fileRoot){
        return Arrays.asList(new ImagingVocabGenerator(fileRoot), new SpeakingVocabGenerator(fileRoot), new WritingVocabGenerator(fileRoot));
    }

    public static final Integer VOCAB_TYPE__IMAGING = 1;
    public static final Integer VOCAB_TYPE__SPEAKING = 3;
    public static final Integer VOCAB_TYPE__WRITING = 4;

    public abstract String getPrefix();
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
        deck.setName(getPrefix() + " " + vocabdeck.getName());
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
        valuesMap.put("phrase", vocab.getPhrase());
        valuesMap.put("personalConnection", vocab.getPersonalConnection());
        valuesMap.put("audioPath", fileRoot + vocab.getAudioPath());
        valuesMap.put("imagePath", fileRoot + vocab.getImagePath());
        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        return sub.replace(template);
    }
}