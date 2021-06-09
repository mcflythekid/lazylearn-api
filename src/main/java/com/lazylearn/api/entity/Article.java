package com.lazylearn.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name = "article", indexes = {
        @Index(columnList = "userId")
})
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class Article extends AbstractEntity implements HasUserId {

    @PrePersist
    public void prePersist() {
    }

    private String name;

    @Lob
    private String content;

    private String url;

    private String slug;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userid")
    private User user;

    public String getUserId() {
        return getUser() == null ? null : getUser().getId();
    }

    public void setUserId(String userId) {
        if (getUser() == null) {
            setUser(new User());
        }
        if (getUser().getId() == null) {
            getUser().setId(userId);
        }
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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
