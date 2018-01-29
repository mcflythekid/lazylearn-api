package com.mcflythekid.lazylearncore.indto;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author McFly the Kid
 */
public class DeckCreateInDto {

    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
