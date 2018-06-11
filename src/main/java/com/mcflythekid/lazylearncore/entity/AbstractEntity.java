package com.mcflythekid.lazylearncore.entity;

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
public abstract class AbstractEntity implements Serializable {

    @PrePersist
    public void parentPrePersist(){
        setId(StringUtils2.generateRandomId());
        setCreatedDate(new Date());
    }

    @PreUpdate
    public void parentPreUpdate(){
        setUpdatedDate(new Date());
    }

    @Id
    private String id;
    private Date createdDate;
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
