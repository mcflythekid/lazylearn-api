package com.mcflythekid.lazylearncore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author McFly the Kid
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AppUnauthorizedException extends AppException{
    public AppUnauthorizedException() {
        super();
    }

    public AppUnauthorizedException(String message) {
        super(message);
    }
}
