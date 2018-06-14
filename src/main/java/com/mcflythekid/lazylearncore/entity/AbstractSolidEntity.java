package com.mcflythekid.lazylearncore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mcflythekid.lazylearncore.config.Consts;
import com.mcflythekid.lazylearncore.util.StringUtils2;

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
public abstract class AbstractSolidEntity implements Serializable {

    @PrePersist
    public void parentPrePersist(){
        setId(StringUtils2.generateRandomId());
        setCreatedDate(new Date());
    }

    @Id
    private String id;
    @JsonFormat(pattern = Consts.PARAM_JSON_DATETIMEFORMAT, timezone = Consts.PARAM_JSON_TIMEZONE)
    private Date createdDate;

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
}