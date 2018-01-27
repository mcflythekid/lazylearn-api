package com.mcflythekid.lazylearncore.exception;

import com.mcflythekid.lazylearncore.Utils;
import com.mcflythekid.lazylearncore.outdto.ExceptionOutDto;
import com.mcflythekid.lazylearncore.service.AuthService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


/**
 * @author McFly the Kid
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<Object>(getExceptionOutDto(ex), headers, status);
    }

    @ExceptionHandler(Exception.class)
    public ExceptionOutDto exceptionHandler(Exception e, HttpServletResponse response)  {
        return getExceptionOutDto(e, response);
    }

    private ExceptionOutDto getExceptionOutDto(Exception e, HttpServletResponse response) {
        String id = authService.getRamdomId();
        final String defaultMsg = "An error has occurred";
        logger.error(String.format("[ERROR-ID:%s] %s", id, e.getMessage()), e);

        if (e instanceof AppConflictException) {
            if (response != null) response.setStatus(HttpServletResponse.SC_CONFLICT);
            return new ExceptionOutDto(id, e.getMessage());
        } else if (e instanceof AppForbiddenException) {
            if (response != null) response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return new ExceptionOutDto(id, e.getMessage());
        } else if (e instanceof AppNotFoundException) {
            if (response != null) response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new ExceptionOutDto(id, e.getMessage());
        } else if (e instanceof AppUnauthorizedException) {
            if (response != null) response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return new ExceptionOutDto(id, e.getMessage());
        } else if (e instanceof AppException ){
            if (response != null) response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new ExceptionOutDto(id, e.getMessage());
        }

        if (e instanceof MethodArgumentNotValidException){
            if (response != null) response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            String msg = null;
            try{
                ObjectError objectError = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().get(0);
                msg =  Utils.getField(objectError, "field") + " " + objectError.getDefaultMessage();
            }catch (Exception ex){
                msg = defaultMsg;
            }
            return new ExceptionOutDto(id, msg);
        }

        if (response != null) response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new ExceptionOutDto(id, defaultMsg);
    }

    private ExceptionOutDto getExceptionOutDto(Exception e){
        return getExceptionOutDto(e, null);
    }

    private static final Logger logger = LogManager.getLogger(AppExceptionHandler.class);

    @Autowired
    private AuthService authService;
}
