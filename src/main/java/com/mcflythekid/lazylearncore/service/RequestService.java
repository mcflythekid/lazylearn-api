package com.mcflythekid.lazylearncore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author McFly the Kid
 */
@Service
public class RequestService {

    @Autowired
    private HttpServletRequest request;

    public String getIpAddress(){
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null || ipAddress.isEmpty()){
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
}
