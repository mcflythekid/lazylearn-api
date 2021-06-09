package com.lazylearn.api.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name = "detailed_user")
public class DetailedUser extends AbstractEntity {

    private String email;
    private String ipAddress;
    private String facebookId;
    private String fullName;

    private Integer cards;
    private Integer decks;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getCards() {
        return cards;
    }

    public void setCards(Integer cards) {
        this.cards = cards;
    }

    public Integer getDecks() {
        return decks;
    }

    public void setDecks(Integer decks) {
        this.decks = decks;
    }
}
