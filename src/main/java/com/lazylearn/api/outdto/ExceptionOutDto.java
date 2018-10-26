package com.lazylearn.api.outdto;

/**
 * @author McFly the Kid
 */
public class ExceptionOutDto {

    public ExceptionOutDto(String errorId, String errorMsg) {
        this.errorId = errorId;
        this.errorMsg = errorMsg;
    }

    private String errorId;
    private String errorMsg;

    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
