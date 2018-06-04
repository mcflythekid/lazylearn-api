package com.mcflythekid.lazylearncore.util;

import com.mcflythekid.lazylearncore.config.exception.AppException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

/**
 * @author McFly the Kid
 */
public final class EmailUtils {

    private EmailUtils(){}

    public static void sendTextEmail(String fromAddress, String fromName, String toAddress, String subject, String body) {
        sendEmail(fromAddress, fromName, toAddress, subject, body, false);
    }

    public static void sendHtmlEmail(String fromAddress, String fromName, String toAddress, String subject, String body) {
        sendEmail(fromAddress, fromName, toAddress, subject, body, true);
    }

    private static void sendEmail(String fromAddress, String fromName, String toAddress, String subject, String body, boolean isHtml){
        try {
            JavaMailSender javaMailSender = new JavaMailSenderImpl();
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromAddress, fromName);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setText(body, isHtml);
            javaMailSender.send(message);
        } catch (Exception e){
            throw new AppException("Cannot send email", e);
        }
    }
}