package com.mcflythekid.lazylearncore.exception;

import com.mcflythekid.lazylearncore.Const;
import com.mcflythekid.lazylearncore.Utils;
import com.mcflythekid.lazylearncore.outdto.ExceptionOutDto;
import com.mcflythekid.lazylearncore.outdto.JSON;
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
import java.util.UUID;


/**
 * @author McFly the Kid
 */
@RestControllerAdvice
public class AppExceptionHandler  {

    private static final Logger logger = LogManager.getLogger(AppExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public JSON exceptionHandler(Exception e, HttpServletResponse response)  {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        String msg = String.format(Const.DEFAUL_ERR_FORMAT, UUID.randomUUID().toString());
        logger.error(msg, e);
        return JSON.error(msg);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JSON methodArgumentNotValidExceptionHandler(Exception e, HttpServletResponse response)  {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        String msg;
        try{
            ObjectError objectError = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().get(0);
            msg =  Utils.getField(objectError, "field").toString().toUpperCase() + ": " + objectError.getDefaultMessage();
        }catch (Exception ex){
            msg = "Method Argument Not Valid";
            logger.error(msg, ex);
        }
        return JSON.error(msg);
    }

    @ExceptionHandler(AppException.class)
    public JSON appExceptionHandler(Exception e, HttpServletResponse response)  {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        logger.error(e.getMessage(), e);
        return JSON.error(e.getMessage());
    }
}