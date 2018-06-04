package com.mcflythekid.lazylearncore.config.exception;

import com.mcflythekid.lazylearncore.config.Consts;
import com.mcflythekid.lazylearncore.repo.UserRepo;
import com.mcflythekid.lazylearncore.util.ReflectionUtils;
import com.mcflythekid.lazylearncore.outdto.JSON;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
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
        String msg = String.format(Consts.DEFAUL_ERR_FORMAT, UUID.randomUUID().toString());
        logger.error(msg, e);
        return JSON.error(msg);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JSON methodArgumentNotValidExceptionHandler(Exception e, HttpServletResponse response)  {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        String msg;
        try{
            ObjectError objectError = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().get(0);
            msg =  ReflectionUtils.getField(objectError, "field").toString().toUpperCase() + ": " + objectError.getDefaultMessage();
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