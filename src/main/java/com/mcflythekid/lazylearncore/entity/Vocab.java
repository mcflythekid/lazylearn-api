package com.mcflythekid.lazylearncore.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name="vocab", indexes = {
        @Index(columnList = "userId")
})
public class Vocab extends AbstractEntity {

    private String userId;
    private String word;
    private String phonetic;
    private String gender;
    private String imagePath;
    private String audioPath;
    private String personalConnection;

    public String getUserId() {
        return userId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
