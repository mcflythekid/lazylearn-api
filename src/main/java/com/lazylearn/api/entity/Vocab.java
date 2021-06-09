package com.lazylearn.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lazylearn.api.indto.EncodedFile;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name = "vocab", indexes = {
        @Index(columnList = "userId"),
        @Index(columnList = "vocabdeckId")
})
public class Vocab extends AbstractEntity implements HasUserId {

    @JsonIgnore
    public void generateImagePath(EncodedFile encodedFile) {
        setImagePath("/vocab/" + getUserId() + "/" + getId() + "_image." + encodedFile.getExt());
    }

    @JsonIgnore
    public void generateAudioPath(EncodedFile encodedFile) {
        setAudioPath("/vocab/" + getUserId() + "/" + getId() + "_audio." + encodedFile.getExt());
    }

    private String userId;
    private String vocabdeckId;
    private String word;
    private String phonetic;
    private String phrase;
    private String imagePath;
    private String audioPath;
    private String audioHint;
    private String personalConnection;

    public String getAudioHint() {
        return audioHint;
    }

    public void setAudioHint(String audioHint) {
        this.audioHint = audioHint;
    }

    public String getUserId() {
        return userId;
    }

    public String getVocabdeckId() {
        return vocabdeckId;
    }

    public void setVocabdeckId(String vocabdeckId) {
        this.vocabdeckId = vocabdeckId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public String getPersonalConnection() {
        return personalConnection;
    }

    public void setPersonalConnection(String personalConnection) {
        this.personalConnection = personalConnection;
    }
}
