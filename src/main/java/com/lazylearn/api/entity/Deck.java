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
public class Deck extends AbstractEntity implements HasUserId{

    @PrePersist
    public void prePersist(){
        setType("default");
        setProgramId(Consts.PROGRAM__DEFAULT);
        setArchived(Consts.CARDDECK_UNARCHIVED);
    }

    private String cloneableid;

    private String userId;
    private String name;
    private Integer archived;
    private String trackingId;

    private String vocabdeckId;
    private Integer vocabType;

    private String minpairLanguage;

    private String programId;

    private String type;

    public String getCloneableid() {
        return cloneableid;
    }

    public void setCloneableid(String cloneableid) {
        this.cloneableid = cloneableid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
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

}
