package com.lazylearn.api.outdto;

/**
 * @author McFly the Kid
 */
public class LoginOut {

    private String accessToken;
    private UserData userData;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
