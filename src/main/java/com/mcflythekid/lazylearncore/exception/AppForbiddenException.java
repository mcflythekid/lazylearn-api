package com.mcflythekid.lazylearncore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author McFly the Kid
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class AppForbiddenException extends AppException {

    public AppForbiddenException() {
        super();
    }

    public AppForbiddenException(String message) {
        super(message);
    }
}
