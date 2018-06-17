package com.mcflythekid.lazylearncore.indto.vocab;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author McFly the Kid
 */
public class VocabCreateIn {

    @NotBlank
    private String word;

    @NotBlank
    private String phonetic;

    private String gender;

    @NotBlank
    private String base64Audio;

    @NotBlank
    private String base64Image;

    @NotBlank
    private String personalConnection;

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

    public String getBase64Audio() {
        return base64Audio;
    }

    public void setBase64Audio(String base64Audio) {
        this.base64Audio = base64Audio;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public String getPersonalConnection() {
        return personalConnection;
    }

    public void setPersonalConnection(String personalConnection) {
        this.personalConnection = personalConnection;
    }
}
