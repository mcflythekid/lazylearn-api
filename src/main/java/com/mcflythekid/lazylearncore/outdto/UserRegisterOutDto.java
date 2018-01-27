package com.mcflythekid.lazylearncore.outdto;

import com.mcflythekid.lazylearncore.entity.User;

/**
 * @author McFly the Kid
 */
public class UserRegisterOutDto {

    public UserRegisterOutDto(User user){
        setEmail(user.getEmail());
        setId(user.getId());
    }

    private String id;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
