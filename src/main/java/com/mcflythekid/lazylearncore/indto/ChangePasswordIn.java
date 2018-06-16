package com.mcflythekid.lazylearncore.indto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author McFly the Kid
 */
public class ChangePasswordIn {

    @NotBlank
    private String newRawPassword;

    public String getNewRawPassword() {
        return newRawPassword;
    }

    public void setNewRawPassword(String newRawPassword) {
        this.newRawPassword = newRawPassword;
    }
}
