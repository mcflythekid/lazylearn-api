package com.mcflythekid.lazylearncore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mcflythekid.lazylearncore.config.Consts;
import com.mcflythekid.lazylearncore.repo.UserRepo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name="card", indexes = {
        @Index(columnList = "userId"),
        @Index(columnList = "deckId")
})
public class Card extends AbstractEntity{

    @JsonIgnore
    public boolean isReadyToArchive(){
        return step >= Consts.CARD_STEP_END;
    }

    @JsonIgnore
    public void increaseStep(){
        step++;
    }

    @JsonIgnore
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
