package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.dto.EmailDto;
import com.mcflythekid.lazylearncore.exception.AppException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author McFly the Kid
 */
@Service
public class EmailService {

    public void sendHtml(EmailDto emailDto){
        send(emailDto, true);
    }

    public void sendText(EmailDto emailDto){
        send(emailDto, false);
    }

    /**
     * https://docs.spring.io/spring/docs/4.3.13.RELEASE/spring-framework-reference/htmlsingle/#mail
     *
     * @param emailDto
     * @param isHtml
     * @throws Exception
     */
    private void send(EmailDto emailDto, boolean isHtml){
        try {
            JavaMailSender javaMailSender = new JavaMailSenderImpl();
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(emailDto.getFrom(), emailDto.getFromPerson());
            helper.setTo(emailDto.getTo());
            helper.setSubject(emailDto.getSubject());
            helper.setText(emailDto.getBody(), isHtml);
            javaMailSender.send(message);
        } catch (Exception e){
            throw new AppException("Cannot send email", e);
        }
    }
}
