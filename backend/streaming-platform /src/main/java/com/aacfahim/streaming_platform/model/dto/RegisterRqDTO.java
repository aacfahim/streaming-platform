package com.aacfahim.streaming_platform.model.dto;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class RegisterRqDTO {
    private String username;
    private String password;

//    @Nullable
//    private String role; // Example: ROLE_USER or ROLE_ADMIN
}
