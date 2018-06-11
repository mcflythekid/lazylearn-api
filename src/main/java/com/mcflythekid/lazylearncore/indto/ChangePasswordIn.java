package com.mcflythekid.lazylearncore.indto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author McFly the Kid
 */
public class ChangePasswordIn {

    @NotBlank
    private String rawPassword;

    public String getRawPassword() {
        return rawPassword;
    }

    public void setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
    }
}
