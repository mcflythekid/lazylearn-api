package com.lazylearn.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lazylearn.api.config.Consts;

import javax.persistence.*;
import java.util.Date;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name = "article", indexes = {
        @Index(columnList = "userId")
})
public class Article extends AbstractEntity {

    @PrePersist
    public void prePersist(){
        setShared(Consts.CARDDECK_UNSHARED);
    }

    private String userId;
    private String name;

    @Lob
    private String content;

    private String url;
    private String category;
    private Integer shared;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userid")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getShared() {
        return shared;
    }

    public void setShared(Integer shared) {
        this.shared = shared;
    }
}
