package com.mcflythekid.lazylearncore.entity;

import com.mcflythekid.lazylearncore.config.Consts;

import javax.persistence.*;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name="deck", indexes = {
        @Index(columnList = "userId")
})
public class Deck extends AbstractEntity {

    @PrePersist
    public void prePersist(){
        setArchived(Consts.CARDDECK_UNARCHIVED);
    }

    private String userId;
    private String name;
    private Integer archived;

    private String vocabdeckId;
    private Integer vocabType;

    public String getVocabdeckId() {
        return vocabdeckId;
    }

    public void setVocabdeckId(String vocabdeckId) {
        this.vocabdeckId = vocabdeckId;
    }

    public Integer getVocabType() {
        return vocabType;
    }

    public void setVocabType(Integer vocabType) {
        this.vocabType = vocabType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getArchived() {
        return archived;
    }

    public void setArchived(Integer archived) {
        this.archived = archived;
    }
}
