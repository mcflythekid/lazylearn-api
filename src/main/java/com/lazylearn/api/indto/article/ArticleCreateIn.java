package com.lazylearn.api.indto.article;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Lob;
import javax.validation.constraints.Pattern;

/**
 * @author McFly the Kid
 */
public class ArticleCreateIn {

    @NotBlank
    private String name;

    @NotBlank
    private String content;

    private String url;

    @NotBlank
    @Pattern(regexp = "^([a-z]{2,10})$", message = "Invalid language name. Allows: a-z")
    private String category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}