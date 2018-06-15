package com.mcflythekid.lazylearncore.entity;

import javax.persistence.*;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name="todo", indexes = {
    @Index(columnList = "userId")
})
public class Todo extends AbstractEntity {

    @PrePersist
    public void prePersist(){
        setDone(0);
    }

    private String userId;
    private Integer done;
    @Lob
    private String data;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
