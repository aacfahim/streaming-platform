package com.aacfahim.streaming_platform.service;

import com.aacfahim.streaming_platform.security.model.entity.OTP;
import jakarta.mail.MessagingException;

import java.util.Optional;

public interface IEmailService {
    void sendOtp(String to, String otp);

}
