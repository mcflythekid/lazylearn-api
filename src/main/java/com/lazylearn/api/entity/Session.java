package com.lazylearn.api.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name = "session", indexes = {
        @Index(columnList = "userId")
})
public class Session extends AbstractSolidEntity {

    private String userId;
    @Lob
    private String clientData;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClientData() {
        return clientData;
    }

    public void setClientData(String clientData) {
        this.clientData = clientData;
    }
}
