package com.lazylearn.api.indto;

import org.hibernate.validator.constraints.NotBlank;

public class BasicIn {

    @NotBlank
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
