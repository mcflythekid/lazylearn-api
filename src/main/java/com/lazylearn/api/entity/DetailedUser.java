package com.lazylearn.api.entity;

import javax.persistence.Column;
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
    @Column(columnDefinition = "NUMERIC(19,0)")
    private Long cards;
    @Column(columnDefinition = "NUMERIC(19,0)")
    private Long decks;

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
