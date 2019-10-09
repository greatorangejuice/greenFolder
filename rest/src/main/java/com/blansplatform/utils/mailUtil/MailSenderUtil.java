/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.utils.mailUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderUtil {

    private final JavaMailSender mailSender;
    private static final String EMAIL_CONFIRM_MESSAGE = "Hello, Welcome to greenFolder. Please visit next link http://localhost:8080/activate/";

    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    public MailSenderUtil(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String emailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }

    public String activationEmailBuilder(String activationCode) {
        return EMAIL_CONFIRM_MESSAGE + activationCode;
    }
}
