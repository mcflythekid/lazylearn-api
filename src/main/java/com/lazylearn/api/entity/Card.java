package com.lazylearn.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lazylearn.api.config.Consts;
import com.lazylearn.api.util.CustomDateUtils;
import org.apache.commons.lang3.time.DateUtils;

import javax.persistence.*;
import java.util.Date;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name="card")
public class
Card extends AbstractEntity implements HasUserId{

    @JsonIgnore
    public boolean isExpired(){
        return getWakeupOn().before(new Date());
    }

    @JsonIgnore
    public boolean isInBegin(){
        return getStep() == Consts.STEP_BEGIN;
    }

    public long getExpiredDays(){
        if (getWakeupOn().before(new Date())){
            return 0;
        }
        return CustomDateUtils.getDifferenceDays(getWakeupOn(), new Date());
    }

    ///////////////////////////////////////
    ///////////////////////////////////////
    ///////////////////////////////////////

    @PrePersist
    public void prePersist(){
        setSm2Ef(Consts.SM2_EF_INIT);
        setWakeupOn(new Date());
        setStep(Consts.STEP_BEGIN);
        setArchived(Consts.CARDDECK_UNARCHIVED);
    }

    @Lob
    private String front;
    @Lob
    private String back;
    @JsonFormat(pattern = Consts.PARAM_JSON_DATETIMEFORMAT, timezone = Consts.PARAM_JSON_TIMEZONE)
    private Date wakeupOn;
    @JsonFormat(pattern = Consts.PARAM_JSON_DATETIMEFORMAT, timezone = Consts.PARAM_JSON_TIMEZONE)
    private Date learnedOn;
    private String deckId;
    private String userId;
    private Integer step;
    private Integer archived;

    private String vocabId;
    private String minpairLanguage;
    private Double sm2Ef;
    private Integer sm2LatestSpace;

    private String articleId;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public Double getSm2Ef() {
        return sm2Ef;
    }

    public void setSm2Ef(Double sm2Ef) {
        this.sm2Ef = sm2Ef;
    }

    public Integer getSm2LatestSpace() {
        return sm2LatestSpace;
    }

    public void setSm2LatestSpace(Integer sm2LatestSpace) {
        this.sm2LatestSpace = sm2LatestSpace;
    }

    public String getMinpairLanguage() {
        return minpairLanguage;
    }

    public void setMinpairLanguage(String minpairLanguage) {
        this.minpairLanguage = minpairLanguage;
    }

    public String getVocabId() {
        return vocabId;
    }

    public void setVocabId(String vocabId) {
        this.vocabId = vocabId;
    }

    public Integer getArchived() {
        return archived;
    }

    public void setArchived(Integer archived) {
        this.archived = archived;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public Date getWakeupOn() {
        return wakeupOn;
    }

    public void setWakeupOn(Date wakeupOn) {
        this.wakeupOn = wakeupOn;
    }

    public String getDeckId() {
        return deckId;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getLearnedOn() {
        return learnedOn;
    }

    public void setLearnedOn(Date learnedOn) {
        this.learnedOn = learnedOn;
    }
}
