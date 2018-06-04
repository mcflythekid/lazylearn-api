package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.config.Consts;
import com.mcflythekid.lazylearncore.config.exception.AppException;
import com.mcflythekid.lazylearncore.entity.ForgetPassword;
import com.mcflythekid.lazylearncore.entity.User;
import com.mcflythekid.lazylearncore.indto.ForgetPasswordCreateInDto;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.repo.ForgetPasswordRepo;
import com.mcflythekid.lazylearncore.repo.UserRepo;
import com.mcflythekid.lazylearncore.util.EmailUtils;
import com.mcflythekid.lazylearncore.util.StringUtils2;
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
public class ForgetPasswordService {

    @Autowired
    private ForgetPasswordRepo forgetPasswordRepo;
    @Autowired
    private UserRepo userRepo;
    @Value("${format.reset-password-url}")
    private String resetPasswordUrlFormat;

    @Transactional(rollbackFor = Exception.class)
    public JSON create(ForgetPasswordCreateInDto forgetPasswordCreateInDto) {
        User user = userRepo.findByEmail(forgetPasswordCreateInDto.getEmail());
        if (user == null) throw new AppException("Email not found");

        ForgetPassword forgetPassword = new ForgetPassword();
        forgetPassword.setId(StringUtils2.generateRandomId());
        forgetPassword.setCreatedOn(new Date());
        forgetPassword.setCurrentEmail(forgetPasswordCreateInDto.getEmail());
        forgetPassword.setIpAddress(forgetPasswordCreateInDto.getIpAddress());
        forgetPassword.setStatus(Consts.FORGETPASSWORD_STATUS_NEW);
        forgetPassword.setUserId(user.getId());
        forgetPassword.setExpiredOn(DateUtils.addDays(new Date(), Consts.FORGETPASSWORD_EXPIRED_DAYS));
        forgetPasswordRepo.save(forgetPassword);

        EmailUtils.sendHtmlEmail("support@lazylearn.com", "Lazylearn Team",
                forgetPassword.getCurrentEmail(), "Reset your password",  getEmailHtml(forgetPassword));
        return JSON.ok();
    }

    private String getEmailHtml(ForgetPassword forgetPassword){
        String url = String.format(resetPasswordUrlFormat, forgetPassword.getId());
        return String.format("<a href='%s'>Please click here</a>", url);
    }
}