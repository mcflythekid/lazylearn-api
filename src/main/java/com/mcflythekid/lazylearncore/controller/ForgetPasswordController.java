package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.indto.ForgetPasswordCreateInDto;
import com.mcflythekid.lazylearncore.indto.UserResetPasswordInDto;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.service.ForgetPasswordService;
import com.mcflythekid.lazylearncore.service.RequestService;
import com.mcflythekid.lazylearncore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author McFly the Kid
 */
@RestController
public class ForgetPasswordController {

    @PostMapping("/forget-password")
    public JSON create(@Valid @RequestBody ForgetPasswordCreateInDto forgetPasswordCreateInDto) {
        forgetPasswordCreateInDto.setIpAddress(requestService.getIpAddress());
        return forgetPasswordService.create(forgetPasswordCreateInDto);
    }

    @PutMapping("/user/by-forget-password/{forgetPasswordId}/password")
    public JSON resetPassword(
            @PathVariable("forgetPasswordId") String forgetPasswordId,
            @Valid @RequestBody UserResetPasswordInDto userResetPasswordInDto) {
        return userService.resetPassword(forgetPasswordId, userResetPasswordInDto);
    }

    @Autowired
    private ForgetPasswordService forgetPasswordService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private UserService userService;
}

