package com.lazylearn.api.indto.auth;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author McFly the Kid
 */
public class ResetPasswordIn {

    @NotBlank
    private String resetId;

    @NotBlank
    private String newRawPassword;

    public String getResetId() {
        return resetId;
    }

    public void setResetId(String resetId) {
        this.resetId = resetId;
    }

    public String getNewRawPassword() {
        return newRawPassword;
    }

    public void setNewRawPassword(String newRawPassword) {
        this.newRawPassword = newRawPassword;
    }
}
