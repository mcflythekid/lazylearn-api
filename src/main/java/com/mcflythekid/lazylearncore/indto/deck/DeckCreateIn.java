package com.mcflythekid.lazylearncore.indto.deck;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author McFly the Kid
 */
public class DeckCreateIn {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
