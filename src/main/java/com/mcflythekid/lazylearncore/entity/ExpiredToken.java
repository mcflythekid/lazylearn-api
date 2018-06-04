package com.mcflythekid.lazylearncore.entity;

import com.mcflythekid.lazylearncore.util.JwtUtils;
import io.jsonwebtoken.Claims;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name = "expired_token")
public class ExpiredToken implements Serializable {

    public ExpiredToken(){}

    public ExpiredToken(String jwtToken){
        Claims claims = JwtUtils.parse(jwtToken);
        userId = claims.getSubject();
        createdOn = new Date();
        expiration = claims.getExpiration();
        signature = JwtUtils.getSignature(jwtToken);
    }

    @Id
    private String signature;
    private String userId;
    private Date expiration;
    private Date createdOn;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
