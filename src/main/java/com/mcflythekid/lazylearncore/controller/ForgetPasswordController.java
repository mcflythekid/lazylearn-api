package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.indto.ForgetPasswordCreateInDto;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.service.ForgetPasswordService;
import com.mcflythekid.lazylearncore.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author McFly the Kid
 */
@RestController
@RequestMapping("/forget-password")
public class ForgetPasswordController {

    @PostMapping
    public JSON create(@Valid @RequestBody ForgetPasswordCreateInDto forgetPasswordCreateInDto){
        forgetPasswordCreateInDto.setIpAddress(requestService.getIpAddress());
        return forgetPasswordService.create(forgetPasswordCreateInDto);
    }

    @Autowired
    private ForgetPasswordService forgetPasswordService;

    @Autowired
    private RequestService requestService;
}
