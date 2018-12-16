package com.lazylearn.api.entity;

import com.lazylearn.api.config.Consts;

import javax.persistence.*;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name="deck", indexes = {
    @Index(columnList = "userId"),
    @Index(columnList = "vocabdeckId"),
}, uniqueConstraints = {
    @UniqueConstraint(columnNames = {"vocabdeckId", "vocabType"})
})
public class Deck extends AbstractEntity {

    @PrePersist
    public void prePersist(){
        setProgramId(Consts.STEP_PROGRAM__PIMSLEUR);
        setArchived(Consts.CARDDECK_UNARCHIVED);
        setShared(Consts.CARDDECK_UNSHARED);
    }

    private String userId;
    private String name;
    private Integer archived;
    private String trackingId;
    private Integer shared;

    private String vocabdeckId;
    private Integer vocabType;

    private String minpairLanguage;
    private String articleCategory;

    private String programId;

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(String articleCategory) {
        this.articleCategory = articleCategory;
    }

    public String getMinpairLanguage() {
        return minpairLanguage;
    }

    public void setMinpairLanguage(String minpairLanguage) {
        this.minpairLanguage = minpairLanguage;
    }

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

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public Integer getShared() {
        return shared;
    }

    public void setShared(Integer shared) {
        this.shared = shared;
    }
}
