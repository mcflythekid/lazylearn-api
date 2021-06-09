package com.lazylearn.api.util;

import com.lazylearn.api.config.EmailConfig;
import com.lazylearn.api.config.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * @author McFly the Kid
 */
@Service
public class EmailService {

    @Autowired
    @Qualifier(EmailConfig.MAIL_SENDER)
    private JavaMailSender javaMailSender;

    private EmailService() {
    }

    public void sendTextEmail(String fromAddress, String fromName, String toAddress, String subject, String body) {
        sendEmail(fromAddress, fromName, toAddress, subject, body, false);
    }

    public void sendHtmlEmail(String fromAddress, String fromName, String toAddress, String subject, String body) {
        sendEmail(fromAddress, fromName, toAddress, subject, body, true);
    }

    private void sendEmail(String fromAddress, String fromName, String toAddress, String subject, String body, boolean isHtml) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromAddress, fromName);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setText(body, isHtml);
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new AppException("Cannot send email", e);
        }
    }
}