package com.mcflythekid.lazylearncore.indto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author McFly the Kid
 */
public class RegisterIn {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String rawPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public void setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
    }
}
