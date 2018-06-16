package com.mcflythekid.lazylearncore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mcflythekid.lazylearncore.config.Consts;
import com.mcflythekid.lazylearncore.repo.UserRepo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
