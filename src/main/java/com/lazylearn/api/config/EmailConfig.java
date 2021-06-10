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

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
