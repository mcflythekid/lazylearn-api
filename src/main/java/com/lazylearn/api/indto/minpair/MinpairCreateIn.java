package com.lazylearn.api.indto.minpair;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * @author McFly the Kid
 */
public class MinpairCreateIn {

    @NotBlank
    private String audio1;

    @NotBlank
    private String audio2;

    @NotBlank
    private String word1;

    @NotBlank
    private String word2;

    @NotBlank
    private String phonetic1;

    @NotBlank
    private String phonetic2;

    @NotBlank
    @Pattern(regexp = "^[a-z]{2,10}$")
    private String language;

    public String getAudio1() {
        return audio1;
    }

    public void setAudio1(String audio1) {
        this.audio1 = audio1;
    }

    public String getAudio2() {
        return audio2;
    }

    public void setAudio2(String audio2) {
        this.audio2 = audio2;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
