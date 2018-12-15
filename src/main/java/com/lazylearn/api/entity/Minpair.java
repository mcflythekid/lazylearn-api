package com.lazylearn.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name = "minpair", indexes = {
        @Index(columnList = "userId")
})
public class Minpair extends AbstractEntity {

    @JsonIgnore
    public void generateAudioPaths(){
        setAudioPath1("/minpair/" + getUserId() + "/" + getId() + "_audio1.mp3");
        setAudioPath2("/minpair/" + getUserId() + "/" + getId() + "_audio2.mp3");
    }

    private String userId;
    private String word1;
    private String word2;
    private String phonetic1;
    private String phonetic2;
    private String audioPath1;
    private String audioPath2;
    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWord1() {
        return word1;
    }

    public void setWord1(String word1) {
        this.word1 = word1;
    }

    public String getWord2() {
        return word2;
    }

    public void setWord2(String word2) {
        this.word2 = word2;
    }

    public String getPhonetic1() {
        return phonetic1;
    }

    public void setPhonetic1(String phonetic1) {
        this.phonetic1 = phonetic1;
    }

    public String getPhonetic2() {
        return phonetic2;
    }

    public void setPhonetic2(String phonetic2) {
        this.phonetic2 = phonetic2;
    }

    public String getAudioPath1() {
        return audioPath1;
    }

    public void setAudioPath1(String audioPath1) {
        this.audioPath1 = audioPath1;
    }

    public String getAudioPath2() {
        return audioPath2;
    }

    public void setAudioPath2(String audioPath2) {
        this.audioPath2 = audioPath2;
    }
}
