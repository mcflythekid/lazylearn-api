package com.lazylearn.api.indto.auth;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author McFly the Kid
 */
public class LoginFacebookIn {

    @NotBlank
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
