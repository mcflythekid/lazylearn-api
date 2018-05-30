package com.mcflythekid.lazylearncore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mcflythekid.lazylearncore.Const;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name = "vdeck")
public class VDeck{

    @Id
    private String id;

    private String userId;
    private String name;

    @JsonFormat(pattern = Const.PARAM_JSON_DATETIMEFORMAT, timezone = Const.PARAM_JSON_TIMEZONE)
    private Date createdOn;

    @JsonFormat(pattern = Const.PARAM_JSON_DATETIMEFORMAT, timezone = Const.PARAM_JSON_TIMEZONE)
    private Date updatedOn;

    @Column(columnDefinition = "decimal(19,0)")
    private Long totalCard;

    @Column(columnDefinition = "decimal(19,0)")
    private Long totalTimeupCard;

    private Integer archived;

    public Integer getArchived() {
        return archived;
    }

    public void setArchived(Integer archived) {
        this.archived = archived;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Long getTotalCard() {
        return totalCard;
    }

    public void setTotalCard(Long totalCard) {
        this.totalCard = totalCard;
    }

    public Long getTotalTimeupCard() {
        return totalTimeupCard;
    }

    public void setTotalTimeupCard(Long totalTimeupCard) {
        this.totalTimeupCard = totalTimeupCard;
    }
}
