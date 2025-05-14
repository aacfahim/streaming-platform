package com.aacfahim.streaming_platform.service;

import jakarta.mail.MessagingException;

public interface IOTPService {
    void generateAndSendOTP(String email);

    boolean verifyOTP(String email, String otp);

    String generateOTP();
}
