package com.lazylearn.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lazylearn.api.config.Consts;
import com.lazylearn.api.util.StringUtils2;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.util.Date;

/**
 * @author McFly the Kid
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @PrePersist
    public void parentPrePersist(){
        setId(StringUtils2.generateReadableId(getClass().getSimpleName(), 36));
        setCreatedDate(new Date());
    }

    @PreUpdate
    public void parentPreUpdate(){
        setUpdatedDate(new Date());
    }

    @Id
    private String id;
    @JsonFormat(pattern = Consts.PARAM_JSON_DATETIMEFORMAT, timezone = Consts.PARAM_JSON_TIMEZONE)
    private Date createdDate;
    @JsonFormat(pattern = Consts.PARAM_JSON_DATETIMEFORMAT, timezone = Consts.PARAM_JSON_TIMEZONE)
    private Date updatedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
