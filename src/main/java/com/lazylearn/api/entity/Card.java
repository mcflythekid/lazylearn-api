package com.lazylearn.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lazylearn.api.config.Consts;
import org.apache.commons.lang3.time.DateUtils;

import javax.persistence.*;
import java.util.Date;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name="card", indexes = {
        @Index(columnList = "userId"),
        @Index(columnList = "deckId"),
        @Index(columnList = "vocabId")
})
public class Card extends AbstractEntity{

    @PrePersist
    public void prePersist(){
        setWakeupOn(new Date());
        setStep(Consts.CARD_STEP_BEGIN);
        setArchived(Consts.CARDDECK_UNARCHIVED);
    }

    public void setWakeupDays(Integer days){
        setWakeupOn(DateUtils.addDays(new Date(), days));
    }

    public void increaseStep(){
        if (getStep() == null || getStep() < Consts.CARD_STEP_BEGIN){
            setStep(Consts.CARD_STEP_BEGIN);
        }
        setStep(getStep() + 1);
    }

    public void resetStep(){
        step = Consts.CARD_STEP_BEGIN;
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
    private String articleCategory;
    private Double sm2Ef;
    private Integer sm2LatestSpace;

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

    public String getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(String articleCategory) {
        this.articleCategory = articleCategory;
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
