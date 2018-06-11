package com.mcflythekid.lazylearncore.entity;

import com.mcflythekid.lazylearncore.util.StringUtils2;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @author McFly the Kid
 */
@Entity
@Table(
    name="user",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}),
        @UniqueConstraint(columnNames = {"facebookId"})
    }
)
public class User implements Serializable {

    @PrePersist
    public void prePersist(){
        setId(StringUtils2.generateRandomId());
        setCreatedOn(new Date());
        setJtv(UUID.randomUUID().toString());
    }

    @PreUpdate
    public void preUpdate(){
        setUpdatedOn(new Date());
    }

    @Id
    private String id;
    private String email;
    private String encodedPassword;
    private Date createdOn;
    private Date updatedOn;
    private String ipAddress;
    private String jtv;
    private String facebookId;
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getJtv() {
        return jtv;
    }

    public void setJtv(String jtv) {
        this.jtv = jtv;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
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