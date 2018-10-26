package com.lazylearn.api.outdto;

/**
 * @author McFly the Kid
 */
public class ChartRow {

    public ChartRow(String stepName, Long correct, Long timeup) {
        this.stepName = stepName;
        this.correct = correct;
        this.timeup = timeup;
    }

    private String stepName;
    private Long correct;
    private Long timeup;


    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public Long getCorrect() {
        return correct;
    }

    public void setCorrect(Long correct) {
        this.correct = correct;
    }

    public Long getTimeup() {
        return timeup;
    }

    public void setTimeup(Long timeup) {
        this.timeup = timeup;
    }
}
