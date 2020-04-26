package com.lazylearn.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name = "minpair_file")
@Getter
@Setter
public class MinpairFile extends AbstractEntity implements HasUserId{

    @JsonIgnore
    public void generateAudioPaths(){
        setAudioPath(String.format("/minpair_file/%s/%s/%s/%s.mp3", getUserId(), getMinpairId(), getSide(), getId()));
    }

    private String name;
    private String minpairId;
    private Integer side; // 0: left ; 1: right
    private String audioPath;

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


}
