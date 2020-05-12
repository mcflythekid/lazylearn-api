package com.lazylearn.api.indto.vocab;

import com.lazylearn.api.indto.EncodedFile;
import com.lazylearn.api.util.MimeUtils;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

/**
 * @author McFly the Kid
 */
public class VocabCreateIn {

    @NotBlank
    private String vocabdeckId;

    @NotBlank
    private String word;

    @NotBlank
    private String phonetic;

    @NotBlank
    private String personalConnection;

    @NotBlank
    private String phrase;

    private String audioHint;

    @NotNull
    private EncodedFile encodedImage;

    @NotNull
    private EncodedFile encodedAudio;

    @AssertTrue(message = "Must be a valid image file")
    public boolean getImage(){
        return MimeUtils.validateMime(encodedImage.getContent(), "image/.+");
    }

    @AssertTrue(message = "Must me a valid audio file")
    public boolean getAudio(){
        return MimeUtils.validateMime(encodedAudio.getContent(), "audio/.+");
    }


    public String getAudioHint() {
        return audioHint;
    }

    public void setAudioHint(String audioHint) {
        this.audioHint = audioHint;
    }

    public String getVocabdeckId() {
        return vocabdeckId;
    }

    public void setVocabdeckId(String vocabdeckId) {
        this.vocabdeckId = vocabdeckId;
    }

    public EncodedFile getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(EncodedFile encodedImage) {
        this.encodedImage = encodedImage;
    }

    public EncodedFile getEncodedAudio() {
        return encodedAudio;
    }

    public void setEncodedAudio(EncodedFile encodedAudio) {
        this.encodedAudio = encodedAudio;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    

    public String getPersonalConnection() {
        return personalConnection;
    }

    public void setPersonalConnection(String personalConnection) {
        this.personalConnection = personalConnection;
    }

    /**
     * @return the phrase
     */
    public String getPhrase() {
        return phrase;
    }

    /**
     * @param phrase the phrase to set
     */
    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
}
