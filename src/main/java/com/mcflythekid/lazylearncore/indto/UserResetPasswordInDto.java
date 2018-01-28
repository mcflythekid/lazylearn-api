package com.mcflythekid.lazylearncore.indto;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author McFly the Kid
 */
public class UserResetPasswordInDto {

    @NotEmpty
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
