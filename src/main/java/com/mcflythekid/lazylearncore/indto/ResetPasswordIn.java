package com.mcflythekid.lazylearncore.indto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author McFly the Kid
 */
public class ResetPasswordIn {

    @NotBlank
    private String forgetPasswordId;

    @NotBlank
    private String rawPassword;

    public String getForgetPasswordId() {
        return forgetPasswordId;
    }

    public void setForgetPasswordId(String forgetPasswordId) {
        this.forgetPasswordId = forgetPasswordId;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public void setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
    }
}
