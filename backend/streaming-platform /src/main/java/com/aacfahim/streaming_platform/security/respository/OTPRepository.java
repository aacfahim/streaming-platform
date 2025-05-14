package com.aacfahim.streaming_platform.security.respository;

import com.aacfahim.streaming_platform.security.model.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OTPRepository extends JpaRepository<OTP, Long> {
    Optional<OTP> findByEmailAndCodeAndUsedFalse(String email, String code);

}
