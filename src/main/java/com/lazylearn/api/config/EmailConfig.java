package com.lazylearn.api.config;

import com.lazylearn.api.config.env.WiredEnv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @author 4nha
 * Date: 2020-05-03
 */
@Configuration
public class EmailConfig {

    @Autowired
    private WiredEnv wiredEnv;

    public static final String MAIL_SENDER = "MAIL_SENDER";

    @Bean(MAIL_SENDER)
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(wiredEnv.getMailServer());
        mailSender.setPort(wiredEnv.getMailPort());
        mailSender.setUsername(wiredEnv.getMailUsername());
        mailSender.setPassword(wiredEnv.getMailPassword());

        Properties mailProp = mailSender.getJavaMailProperties();
        mailProp.put("mail.transport.protocol", "smtp");
        mailProp.put("mail.smtp.auth", "true");
        mailProp.put("mail.smtp.starttls.enable", "true");
        mailProp.put("mail.smtp.starttls.required", "true");
        mailProp.put("mail.debug", "true");
        mailProp.put("mail.smtp.ssl.enable", "true");
        mailProp.put("mail.smtp.user", wiredEnv.getMailUsername());
        return mailSender;
    }
}
