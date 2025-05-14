package com.aacfahim.streaming_platform.service.impl;

import com.aacfahim.streaming_platform.security.model.entity.OTP;
import com.aacfahim.streaming_platform.security.respository.OTPRepository;
import com.aacfahim.streaming_platform.service.IOTPService;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OTPService implements IOTPService {

    private final OTPRepository otpRepository;
    private final EmailService emailService;

    public OTPService(OTPRepository otpRepository, EmailService emailService) {
        this.otpRepository = otpRepository;
        this.emailService = emailService;
    }

    @Override
    public void generateAndSendOTP(String email)  {
        String otp = generateOTP();

        OTP otpEntity = OTP.builder()
                .email(email)
                .code(otp)
                .expiryTime(LocalDateTime.now().plusMinutes(5))
                .used(false)
                .build();

        otpRepository.save(otpEntity);
        emailService.sendOtp(email, otp);

    }

    @Override
    public boolean verifyOTP(String email, String otp) {
        return otpRepository.findByEmailAndCodeAndUsedFalse(email, otp)
                .filter(otpEntity -> LocalDateTime.now().isBefore(otpEntity.getExpiryTime()))
                .map(otpEntity -> {
                    otpEntity.setUsed(true);
                    otpRepository.save(otpEntity);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public String generateOTP() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(999999));
    }
}
