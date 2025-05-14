package com.aacfahim.streaming_platform.service;

import com.aacfahim.streaming_platform.model.dto.RegisterRqDTO;
import com.aacfahim.streaming_platform.model.dto.otp.InitiateRegisterDTO;
import com.aacfahim.streaming_platform.model.dto.otp.OtpVerificationDTO;
import com.aacfahim.streaming_platform.security.model.entity.User;

public interface IUserService {

    User register(RegisterRqDTO requestDto);

    void initiateRegistration(InitiateRegisterDTO dto);

    User completeRegistration(OtpVerificationDTO dto);
}
