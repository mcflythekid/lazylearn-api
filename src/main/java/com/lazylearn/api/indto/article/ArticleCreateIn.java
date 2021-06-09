package com.lazylearn.api.indto.article;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author McFly the Kid
 */
public class ArticleCreateIn {

    @NotBlank
    private String name;

    @NotBlank
    private String content;

    private String url;

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

}
