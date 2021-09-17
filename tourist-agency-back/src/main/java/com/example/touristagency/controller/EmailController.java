package com.example.touristagency.controller;


import com.example.touristagency.service.impl.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/email")
public class EmailController {

    private MailService mailService;

    @Autowired
    public EmailController(MailService mailService){
        this.mailService = mailService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody String email) throws MessagingException, IOException{
        System.out.println("Mejl se salje");
        mailService.sendEmailWithAttachment(email);

        return ResponseEntity.status(HttpStatus.OK).body("Email was send to "+email);
    }
}
