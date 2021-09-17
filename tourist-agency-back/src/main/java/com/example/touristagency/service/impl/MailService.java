package com.example.touristagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.PathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

public class MailService {
    private JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    /**
     * Slanje mejla bez slike
     * @param toAddress
     * @param subject
     * @param bodyText
     * @throws MailException
     */

    public void sendEmail(String toAddress, String subject, String bodyText) throws MailException{
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(toAddress);
        msg.setSubject(subject);
        msg.setText(bodyText);

        javaMailSender.send(msg);
    }
    /**
     * Slanje mejla sa slikom
     * @throws MessagingException
     * @throws IOException
     */
    public void sendEmailWithAttachment(String email) throws MessagingException, IOException{
        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(email);

        helper.setSubject("My traveling reservation");

        helper.setText("<h1>Thank you for making your reservation!<h1>", true);

        helper.addAttachment("my_travel.jpeg", new PathResource("../tourist-agency-back/src/main/resources/travel.jpeg"));

        javaMailSender.send(msg);
    }


}





