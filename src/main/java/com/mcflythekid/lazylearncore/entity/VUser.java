package com.mcflythekid.lazylearncore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mcflythekid.lazylearncore.Const;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name = "vuser")
public class VUser {

    @Id
    private String id;

    @JsonFormat(pattern = Const.PARAM_JSON_DATETIMEFORMAT, timezone = Const.PARAM_JSON_TIMEZONE)
    private Date createdOn;

    @JsonFormat(pattern = Const.PARAM_JSON_DATETIMEFORMAT, timezone = Const.PARAM_JSON_TIMEZONE)
    private Date updatedOn;

    private String email;
    private String registerIpAddress;

    @Column(columnDefinition = "NUMERIC(19,0)")
    private Long cards;

    @Column(columnDefinition = "NUMERIC(19,0)")
    private Long decks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegisterIpAddress() {
        return registerIpAddress;
    }

    public void setRegisterIpAddress(String registerIpAddress) {
        this.registerIpAddress = registerIpAddress;
    }

    public Long getCards() {
        return cards;
    }

    public void setCards(Long cards) {
        this.cards = cards;
    }

    public Long getDecks() {
        return decks;
    }

    public void setDecks(Long decks) {
        this.decks = decks;
    }
}
