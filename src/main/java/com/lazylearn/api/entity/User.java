package com.lazylearn.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name="user", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"}),
    @UniqueConstraint(columnNames = {"facebookId"})
})
public class User extends AbstractEntity implements HasUserId{

    @PrePersist
    public void prePersist(){
        setSessionKey(UUID.randomUUID().toString());
    }

    private String email;

    @JsonIgnore
    private String encodedPassword;

    @JsonIgnore
    private String ipAddress;

    @JsonIgnore
    private String sessionKey;

    @JsonIgnore
    private String facebookId;

    private String fullName;

    private String timezone;

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

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

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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

    @Override
    public String getUserId() {
        return getId();
    }
}