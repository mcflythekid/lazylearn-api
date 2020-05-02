package com.lazylearn.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name = "minpair", indexes = {
        @Index(columnList = "userId")
})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Minpair extends AbstractEntity implements HasUserId{

    @JsonIgnore
    public String getDirPath(){
        if (isBlank(userid) || isBlank(getId())){
            throw new RuntimeException("userid / id is blank, please init them first");
        }
        return String.format("/minpair_file/%s/%s", userid, getId());
    }

    private String word1;
    private String word2;
    private String phonetic1;
    private String phonetic2;
    private String audioPath1;
    private String audioPath2;
    private String language;
    private String userid;

    @Override
    public String getUserId() {
        return userid;
    }
}
