package com.lazylearn.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lazylearn.api.indto.EncodedFile;
import com.lazylearn.api.util.LazyDateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.text.SimpleDateFormat;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name="vocab_saved")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VocabSaved extends AbstractEntity {

    @JsonIgnore
    public void generateAudioPath(EncodedFile encodedFile){
        setAudioPath("/vocab_saved/" + LazyDateUtil.generateCurrentYyyyMm() + "/" + LazyDateUtil.generateCurrentDd() + "/" + getId() + "_audio." + encodedFile.getExt());
    }

    private String createdBy;
    private String word;
    private String phonetic;
    private String phrase;
    private String audioPath;
    private Integer languageId;
    private Integer platformId;
}
