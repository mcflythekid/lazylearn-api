package com.mcflythekid.lazylearncore.entity;

import com.mcflythekid.lazylearncore.config.Consts;

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
public class Vocabdeck extends AbstractEntity {

    @PrePersist
    public void prePersist(){
        setArchived(Consts.CARDDECK_UNARCHIVED);
    }

    private String userId;
    private String name;
    private Integer archived;

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