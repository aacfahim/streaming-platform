package com.aacfahim.streaming_platform.service.impl;

import com.aacfahim.streaming_platform.model.dto.RegisterRqDTO;
import com.aacfahim.streaming_platform.model.dto.otp.InitiateRegisterDTO;
import com.aacfahim.streaming_platform.model.dto.otp.OtpVerificationDTO;
import com.aacfahim.streaming_platform.security.model.entity.User;
import com.aacfahim.streaming_platform.security.respository.UserRepository;
import com.aacfahim.streaming_platform.service.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OTPService otpService;
    private final Map<String, RegisterRqDTO> pendingRegistrations = new HashMap<>();


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, OTPService otpService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.otpService = otpService;
    }

    @Override
    public User register(RegisterRqDTO dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();

        return userRepository.save(user);
    }

    @Override
    public void initiateRegistration(InitiateRegisterDTO dto) {
        // Check if user already exists
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        // Store registration data
        RegisterRqDTO registerRqDTO = new RegisterRqDTO();
        registerRqDTO.setUsername(dto.getUsername());
        registerRqDTO.setPassword(dto.getPassword());
        pendingRegistrations.put(dto.getUsername(), registerRqDTO);

        // Send OTP
        otpService.generateAndSendOTP(dto.getUsername());
    }

    @Override
    public User completeRegistration(OtpVerificationDTO dto) {
        // Verify OTP
        if (!otpService.verifyOTP(dto.getUsername(), dto.getOtp())) {
            throw new RuntimeException("Invalid or expired OTP");
        }

        // Get pending registration
        RegisterRqDTO registrationData = pendingRegistrations.remove(dto.getUsername());
        if (registrationData == null) {
            throw new RuntimeException("No pending registration found");
        }

        // Register the user
        return register(registrationData);
    }


}
