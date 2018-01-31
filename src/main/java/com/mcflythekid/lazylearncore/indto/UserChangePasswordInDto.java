package com.mcflythekid.lazylearncore.indto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author McFly the Kid
 */
public class UserChangePasswordInDto {

    @NotBlank
    private String newPassword;

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
