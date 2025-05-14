package com.aacfahim.streaming_platform.model.dto.otp;

import lombok.Data;

@Data
public class OtpVerificationDTO {
    private String username;
    private String otp;
}
