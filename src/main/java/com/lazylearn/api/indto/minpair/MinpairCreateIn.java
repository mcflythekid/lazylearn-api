package com.lazylearn.api.indto.minpair;

import com.lazylearn.api.indto.EncodedFile;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author McFly the Kid
 */
public class MinpairCreateIn {

    @NotNull
    private List<EncodedFile> audioFiles1;

    @NotNull
    private List<EncodedFile> audioFiles2;

    @AssertTrue(message = "Audio files is required")
    public boolean isValidFiles(){
        return audioFiles1 != null && audioFiles2 != null
                && !audioFiles1.isEmpty() && !audioFiles2.isEmpty();
    }

    @NotBlank
    private String word1;

    @NotBlank
    private String word2;

    @NotBlank
    private String phonetic1;

    @NotBlank
    private String phonetic2;

    @NotBlank
    @Pattern(regexp = "^([a-z]{2,10})$", message = "Invalid language name. Allows: a-z")
    private String language;

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
