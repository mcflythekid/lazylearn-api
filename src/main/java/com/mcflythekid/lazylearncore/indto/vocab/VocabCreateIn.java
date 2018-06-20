package com.mcflythekid.lazylearncore.indto.vocab;

import com.mcflythekid.lazylearncore.indto.EncodedFile;
import com.mcflythekid.lazylearncore.util.MimeUtils;
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

    private String gender;

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
}
