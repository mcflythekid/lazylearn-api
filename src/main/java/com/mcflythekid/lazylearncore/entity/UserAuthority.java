package com.mcflythekid.lazylearncore.entity;

import com.mcflythekid.lazylearncore.entity.key.UserAuthorityKey;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name = "user_authority")
public class UserAuthority implements Serializable {

    public String getUserId(){
        return key.getUserId();
    }

    public String getAuthority(){
        return key.getAuthority();
    }

    @EmbeddedId
    private UserAuthorityKey key;

    public UserAuthorityKey getKey() {
        return key;
    }

    public void setKey(UserAuthorityKey key) {
        this.key = key;
    }
}
