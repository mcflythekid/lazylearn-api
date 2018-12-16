package com.lazylearn.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name = "detailed_deck")
public class DetailedDeck extends AbstractEntity{

    private String userId;
    private String name;
    @Column(columnDefinition = "decimal(19,0)")
    private Long totalCard;
    @Column(columnDefinition = "decimal(19,0)")
    private Long totalTimeupCard;
    private Integer archived;
    private String vocabdeckId;
    private String vocabdeckName;
    private String minpairLanguage;
    private String articleCategory;
    private String programId;

    public String getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(String articleCategory) {
        this.articleCategory = articleCategory;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getMinpairLanguage() {
        return minpairLanguage;
    }

    public void setMinpairLanguage(String minpairLanguage) {
        this.minpairLanguage = minpairLanguage;
    }

    public String getVocabdeckName() {
        return vocabdeckName;
    }

    public void setVocabdeckName(String vocabdeckName) {
        this.vocabdeckName = vocabdeckName;
    }

    public String getVocabdeckId() {
        return vocabdeckId;
    }

    public void setVocabdeckId(String vocabdeckId) {
        this.vocabdeckId = vocabdeckId;
    }

    public Integer getArchived() {
        return archived;
    }

    public void setArchived(Integer archived) {
        this.archived = archived;
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
