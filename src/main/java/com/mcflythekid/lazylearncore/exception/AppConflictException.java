package com.mcflythekid.lazylearncore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author McFly the Kid
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class AppConflictException extends AppException {
    public AppConflictException() {
        super();
    }

    public AppConflictException(String message) {
        super(message);
    }
}
