package com.mcflythekid.lazylearncore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author McFly the Kid
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AppNotFoundException extends AppException {

    public AppNotFoundException() {
        super();
    }

    public AppNotFoundException(String message) {
        super(message);
    }
}
