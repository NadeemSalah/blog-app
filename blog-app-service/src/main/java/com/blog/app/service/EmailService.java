package com.blog.app.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Async
    public void sendEmail(final @NonNull SimpleMailMessage email) {
        this.javaMailSender.send(email);
    }
}
