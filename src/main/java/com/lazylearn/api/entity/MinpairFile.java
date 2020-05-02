package com.lazylearn.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lazylearn.api.indto.EncodedFile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name = "minpair_file")
@Getter
@Setter
public class MinpairFile extends AbstractEntity implements HasUserId{

    /**
     * Should be called after saved entity
     * @param encodedFile
     */
    @JsonIgnore
    public void generateAudioPaths(Minpair minpair, EncodedFile encodedFile){
        if (isBlank(getId())){
            throw new RuntimeException("Entity not saved before generate audio path");
        }
        setAudioPath(String.format("%s/%s/%s.%s", minpair.getDirPath(), getSide(), getId(), encodedFile.getExt()));
    }

    private String name;

    @Column(name = "minpair_id")
    private String minpairId;

    private Integer side; // 1: left ; 2: right

    @Column(name = "audio_path")
    private String audioPath;

    private String userid;

    @Override
    public String getUserId() {
        return userid;
    }
}
