package com.mcflythekid.lazylearncore.config.exception;

import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.util.ReflectionUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


/**
 * @author McFly the Kid
 */
@RestControllerAdvice
public class AppExceptionHandler {

    private static final Logger logger = LogManager.getRootLogger();


    /*----------------------------------------------------------------------------------------*/
    @ExceptionHandler(Exception.class)
    public JSON exceptionHandler(Exception exception, HttpServletResponse response)  {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        String msg = "Cannot process request. ID:" + UUID.randomUUID().toString();
        logger.error(msg, exception);
        return JSON.error(msg);
    }

    @ExceptionHandler(AppException.class)
    public JSON appExceptionHandler(AppException appException, HttpServletResponse response)  {
        response.setStatus(appException.getHttpStatus());
        logger.error(appException.getMessage(), appException);
        return JSON.error(appException.getMessage());
    }
    /*----------------------------------------------------------------------------------------*/


    /*----------------------------------------------------------------------------------------*/
    @ExceptionHandler(InsufficientAuthenticationException.class)
    public JSON insufficientAuthenticationExceptionHandler(HttpServletResponse response)  {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return JSON.error(HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JSON httpRequestMethodNotSupportedExceptionhandler(HttpServletResponse response)  {
        response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        return JSON.error(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JSON methodArgumentNotValidExceptionHandler(Exception e, HttpServletResponse response)  {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        String msg = HttpStatus.BAD_REQUEST.getReasonPhrase();
        try{
            ObjectError objectError = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().get(0);
            msg =  ReflectionUtils.getField(objectError, "field").toString().toUpperCase() + ": " + objectError.getDefaultMessage();
        }catch (Exception ex){}
        return JSON.error(msg);
    }
    /*----------------------------------------------------------------------------------------*/
}