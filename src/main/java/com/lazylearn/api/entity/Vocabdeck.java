package com.lazylearn.api.entity;

import com.lazylearn.api.config.Consts;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name="vocabdeck", indexes = {
    @Index(columnList = "userId")
})
public class Vocabdeck extends AbstractEntity implements HasUserId{

    @PrePersist
    public void prePersist(){
        setArchived(Consts.CARDDECK_UNARCHIVED);
        setLanguage(Consts.VOCABDECK_LANGUAGE);
    }

    private String userId;
    private String name;
    private Integer archived;
    private String language;

    private String cloneableid;

    //////////////////////////////////////////////////////////////////////////


    public String getCloneableid() {
        return cloneableid;
    }

    public void setCloneableid(String cloneableid) {
        this.cloneableid = cloneableid;
    }

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
