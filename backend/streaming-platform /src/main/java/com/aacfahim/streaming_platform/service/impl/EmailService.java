package com.aacfahim.streaming_platform.service.impl;

import com.aacfahim.streaming_platform.service.IEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import static com.aacfahim.streaming_platform.config.Constant.MAIL_FROM;

@Service
public class EmailService implements IEmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendOtp(String to, String otp) {
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(MAIL_FROM);
            helper.setTo(to);
            helper.setSubject("OTP Verification Code");
            helper.setText("Your OTP code for registration is: " + otp);

            mailSender.send(message);
        }catch (MessagingException e){
            throw new RuntimeException("Failed to send OTP email", e);
        }
    }
}
