package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.Const;
import com.mcflythekid.lazylearncore.dto.EmailDto;
import com.mcflythekid.lazylearncore.entity.ForgetPassword;
import com.mcflythekid.lazylearncore.entity.User;
import com.mcflythekid.lazylearncore.exception.AppNotFoundException;
import com.mcflythekid.lazylearncore.indto.ForgetPasswordCreateInDto;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.repo.ForgetPasswordRepo;
import com.mcflythekid.lazylearncore.repo.UserRepo;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author McFly the Kid
 */
@Service
@Transactional
public class ForgetPasswordService {

    public JSON create(ForgetPasswordCreateInDto forgetPasswordCreateInDto) {
        User user = userRepo.findByEmail(forgetPasswordCreateInDto.getEmail());
        if (user == null) throw new AppNotFoundException("Email not found");

        ForgetPassword forgetPassword = new ForgetPassword();
        forgetPassword.setId(authService.getRamdomId());
        forgetPassword.setCreatedOn(new Date());
        forgetPassword.setCurrentEmail(forgetPasswordCreateInDto.getEmail());
        forgetPassword.setIpAddress(forgetPasswordCreateInDto.getIpAddress());
        forgetPassword.setStatus(Const.FORGETPASSWORD_STATUS_NEW);
        forgetPassword.setUserId(user.getId());
        forgetPassword.setExpiredOn(DateUtils.addDays(new Date(), Const.FORGETPASSWORD_EXPIRED_DAYS));
        forgetPasswordRepo.save(forgetPassword);

        emailService.sendHtml(getForgetPasswordEmail(forgetPassword));

        return JSON.ok();
    }

    private EmailDto getForgetPasswordEmail(ForgetPassword forgetPassword){
        EmailDto emailDto = new EmailDto();
        emailDto.setTo(forgetPassword.getCurrentEmail());
        emailDto.setFromPerson("Lazylearn Team");
        emailDto.setFrom("support@lazylearn.com");
        emailDto.setSubject("Reset your password");
        emailDto.setBody(String.format("<a href='%s%s'>Please click here</a>", appResetPasswordUrl, forgetPassword.getId()));
        return emailDto;
    }

    @Autowired
    private ForgetPasswordRepo forgetPasswordRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthService authService;

    @Autowired
    private EmailService emailService;

    @Value("${app.reset-password-url}")
    private String appResetPasswordUrl;

}
