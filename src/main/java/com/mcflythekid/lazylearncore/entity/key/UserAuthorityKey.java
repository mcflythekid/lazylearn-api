package com.mcflythekid.lazylearncore.entity.key;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author McFly the Kid
 */
@Embeddable
public class UserAuthorityKey implements Serializable {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAuthorityKey)) return false;
        UserAuthorityKey that = (UserAuthorityKey) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(authority, that.authority);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, authority);
    }

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
