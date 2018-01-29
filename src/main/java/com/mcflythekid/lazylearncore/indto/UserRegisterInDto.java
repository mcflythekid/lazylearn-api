package com.mcflythekid.lazylearncore.indto;

import com.mcflythekid.lazylearncore.entity.User;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author McFly the Kid
 */
public class UserRegisterInDto {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    private String registerIpAddress;

    public User getUser(){
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRegisterIpAddress(registerIpAddress);
        return user;
    }

    public String getRegisterIpAddress() {
        return registerIpAddress;
    }

    public void setRegisterIpAddress(String registerIpAddress) {
        this.registerIpAddress = registerIpAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
