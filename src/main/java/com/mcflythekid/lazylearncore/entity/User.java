package com.mcflythekid.lazylearncore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mcflythekid.lazylearncore.config.Consts;
import com.mcflythekid.lazylearncore.repo.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name="user")
public class User implements Serializable {

    @Id
    private String id;
    private String email;
    private String hashedPassword;
    @JsonFormat(pattern = Consts.PARAM_JSON_DATETIMEFORMAT, timezone = Consts.PARAM_JSON_TIMEZONE)
    private Date createdOn;
    @JsonFormat(pattern = Consts.PARAM_JSON_DATETIMEFORMAT, timezone = Consts.PARAM_JSON_TIMEZONE)
    private Date updatedOn;
    private String registerIpAddress;
    private String authorities;

    @Transient
    private String password;
    @JsonIgnore
    List<GrantedAuthority> getGrantedAuthorities() {
        List data = new ArrayList<>();
        for (String authority : authorities.split(",")){
            data.add(new SimpleGrantedAuthority(authority));
        }
        return data;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public String getRegisterIpAddress() {
        return registerIpAddress;
    }

    public void setRegisterIpAddress(String registerIpAddress) {
        this.registerIpAddress = registerIpAddress;
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

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}