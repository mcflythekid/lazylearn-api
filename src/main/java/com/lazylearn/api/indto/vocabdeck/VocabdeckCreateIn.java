package com.lazylearn.api.indto.vocabdeck;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author McFly the Kid
 */
public class VocabdeckCreateIn {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
