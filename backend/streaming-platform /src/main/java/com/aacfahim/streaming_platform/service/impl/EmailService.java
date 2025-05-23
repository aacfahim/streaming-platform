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
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(MAIL_FROM);
            helper.setTo(to);
            helper.setSubject("Your OTP Verification Code");

            String htmlContent = """
            <html>
                <head>
                    <style>
                        @import url('https://fonts.googleapis.com/css2?family=Roboto&display=swap');
                    </style>
                </head>
                <body style="margin: 0; padding: 0; background: linear-gradient(to right, #f8f9fa, #e9ecef); font-family: 'Roboto', sans-serif;">
                    <div style="max-width: 600px; margin: auto; padding: 40px 20px;">
                        <div style="background-color: #ffffff; border-radius: 12px; padding: 40px; box-shadow: 0 8px 16px rgba(0,0,0,0.1); text-align: center;">
                            <h1 style="color: #4a90e2; margin-bottom: 10px;">Verify Your Account</h1>
                            <p style="font-size: 16px; color: #555;">Hi,</p>
                            <p style="font-size: 16px; color: #555;">Use the code below to verify your account:</p>
                            <div style="margin: 30px 0; padding: 20px; background-color: #f1f3f5; border-radius: 8px; display: inline-block;">
                                <span style="font-size: 36px; font-weight: bold; color: #212529; letter-spacing: 4px;">%s</span>
                            </div>
                            <p style="color: #888; font-size: 14px;">This code will expire in <strong>5 minutes</strong>.</p>
                            <hr style="margin: 40px 0; border: none; border-top: 1px solid #dee2e6;">
                            <p style="font-size: 12px; color: #aaa;">If you didn’t request this, please ignore this email.</p>
                        </div>
                    </div>
                </body>
            </html>
        """.formatted(otp);

            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send OTP email", e);
        }
    }

}
