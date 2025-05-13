package com.aacfahim.streaming_platform.security.controller;

import com.aacfahim.streaming_platform.model.dto.RegisterRqDTO;
import com.aacfahim.streaming_platform.security.jwt.JwtService;
import com.aacfahim.streaming_platform.security.model.dto.AuthRequest;
import com.aacfahim.streaming_platform.security.model.dto.AuthResponse;
import com.aacfahim.streaming_platform.service.impl.UserService;
import com.aacfahim.streaming_platform.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody @Validated AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            String token = jwtService.generateToken(userDetails.getUsername());


            AuthResponse authResponse = new AuthResponse(token, userDetails.getUsername());


            ApiResponse<AuthResponse> response = new ApiResponse<>(
                    200,
                    "SUCCESS",
                    "Login successful",
                    authResponse,
                    null
            );

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (BadCredentialsException ex) {
            ApiResponse<AuthResponse> response = new ApiResponse<>(
                    401,
                    "FAILURE",
                    "Invalid username or password",
                    null,
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }


    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody RegisterRqDTO requestDto) {
        userService.register(requestDto);
        return ResponseEntity.ok(
                new ApiResponse<>(200, "SUCCESS", "User registered", null, null)
        );
    }
}