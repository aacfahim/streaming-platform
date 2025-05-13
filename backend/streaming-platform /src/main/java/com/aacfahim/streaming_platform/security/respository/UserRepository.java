package com.aacfahim.streaming_platform.security.respository;

import com.aacfahim.streaming_platform.security.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // used in CustomUserDetailsService

}
