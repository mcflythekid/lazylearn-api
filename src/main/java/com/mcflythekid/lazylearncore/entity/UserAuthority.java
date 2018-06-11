package com.mcflythekid.lazylearncore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mcflythekid.lazylearncore.config.Consts;
import com.mcflythekid.lazylearncore.util.StringUtils2;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author McFly the Kid
 */
@Entity
@Table(
    name = "user_authority",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userId", "authority"})
    }
)
public class UserAuthority implements Serializable {

    @PrePersist
    public void onPrePersist() {
        setCreatedOn(new Date());
        setId(StringUtils2.generateRandomId());
    }

    @Id
    private String id;

    private String userId;

    private String authority;

    @JsonFormat(pattern = Consts.PARAM_JSON_DATETIMEFORMAT, timezone = Consts.PARAM_JSON_TIMEZONE)
    private Date createdOn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
