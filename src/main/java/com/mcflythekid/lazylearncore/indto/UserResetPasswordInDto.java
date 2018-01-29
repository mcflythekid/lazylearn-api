package com.mcflythekid.lazylearncore.indto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author McFly the Kid
 */
public class UserResetPasswordInDto {

    @NotBlank
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
