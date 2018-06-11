package com.mcflythekid.lazylearncore.config.exception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author McFly the Kid
 */
public class AppExceptionTest {

    @Test
    public void testConstructor(){
        AppException appException = new AppException();
        assertEquals(500L, appException.getHttpStatus().longValue());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), appException.getMessage());
    }

    @Test
    public void testConstructorWithStatus(){
        AppException appException = new AppException(100);
        assertEquals(100L, appException.getHttpStatus().longValue());
        assertEquals(HttpStatus.valueOf(100).getReasonPhrase(), appException.getMessage());
    }

    @Test
    public void testConstructorWithMessage(){
        final String message = "a bal bal";
        AppException appException = new AppException(message);
        assertEquals(500L, appException.getHttpStatus().longValue());
        assertEquals(message, appException.getMessage());
    }

    @Test
    public void testConstructorWithStatusAndMessage(){
        final String message = "a bal bal";
        AppException appException = new AppException(100, message);
        assertEquals(100L, appException.getHttpStatus().longValue());
        assertEquals(message, appException.getMessage());
    }

    @Test
    public void testConstructorWithMessageAndThrowable(){
        final String message = "a bal bal";
        final String message2 = "dkmm";
        Exception exception = new Exception(message2);

        AppException appException = new AppException(message, exception);

        assertEquals(500L, appException.getHttpStatus().longValue());
        assertEquals(message, appException.getMessage());
        assertEquals(message2, appException.getCause().getMessage());
    }
}
