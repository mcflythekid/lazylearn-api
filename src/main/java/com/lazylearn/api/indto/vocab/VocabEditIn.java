package com.lazylearn.api.indto.vocab;

import com.lazylearn.api.indto.EncodedFile;
import com.lazylearn.api.util.MimeUtils;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.AssertTrue;

/**
 * @author McFly the Kid
 */
public class VocabEditIn {

    @NotBlank
    private String vocabId;

    @NotBlank
    private String word;

    @NotBlank
    private String phonetic;

    @NotBlank
    private String personalConnection;

    private String gender;

    private EncodedFile encodedImage;

    private EncodedFile encodedAudio;

    @AssertTrue(message = "Must be a valid image file")
    public boolean getImage(){
        if (encodedImage == null){
            return true;
        }
        return MimeUtils.validateMime(encodedImage.getContent(), "image/.+");
    }

    @AssertTrue(message = "Must me a valid audio file")
    public boolean getAudio(){
        if (encodedAudio == null){
            return true;
        }
        return MimeUtils.validateMime(encodedAudio.getContent(), "audio/.+");
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPersonalConnection() {
        return personalConnection;
    }

    public void setPersonalConnection(String personalConnection) {
        this.personalConnection = personalConnection;
    }


    public String getVocabId() {
        return vocabId;
    }

    public void setVocabId(String vocabId) {
        this.vocabId = vocabId;
    }
}
