package com.lazylearn.api.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name = "reset", indexes = {
        @Index(columnList = "userId")
})
public class Reset extends AbstractSolidEntity {

    private String userId;
    private Date expiredDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }
}
