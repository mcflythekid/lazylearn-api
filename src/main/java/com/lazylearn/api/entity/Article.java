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
    }

    private String name;

    @Lob
    private String content;

    private String url;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userid")
    private User user;

    public String getUserId(){
        return getUser() == null ? null : getUser().getId();
    }

    public void setUserId(String userId){
        if (getUser() == null){
            setUser(new User());
        }
        if (getUser().getId() == null){
            getUser().setId(userId);
        }
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
