package com.lazylearn.api.entity;

import javax.persistence.*;

/**
 * @author McFly the Kid
 */
@Entity
@Table( name = "user_authority", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"userId", "authority"})
})
public class UserAuthority extends AbstractSolidEntity {

    private String userId;

    private String authority;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }


}
