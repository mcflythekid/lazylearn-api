package com.mcflythekid.lazylearncore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mcflythekid.lazylearncore.Const;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name="deck")
public class Deck implements Serializable {

    @Id
    private String id;

    private String userId;
    private String name;

    @JsonFormat(pattern = Const.PARAM_JSON_DATETIMEFORMAT, timezone = Const.PARAM_JSON_TIMEZONE)
    private Date createdOn;

    @JsonFormat(pattern = Const.PARAM_JSON_DATETIMEFORMAT, timezone = Const.PARAM_JSON_TIMEZONE)
    private Date updatedOn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
