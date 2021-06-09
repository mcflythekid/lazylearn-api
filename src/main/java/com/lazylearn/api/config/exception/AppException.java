package com.lazylearn.api.config.exception;

import org.springframework.http.HttpStatus;

/**
 * @author McFly the Kid
 */
public class AppException extends RuntimeException {

    private Integer httpStatus;

    public AppException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    public AppException(Integer httpStatus) {
        super(HttpStatus.valueOf(httpStatus).getReasonPhrase());
        this.httpStatus = httpStatus;
    }

    public AppException(int httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public AppException(String message, Throwable e) {
        super(message, e);

    }

    public AppException(String message) {
        super(message);
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }
}
