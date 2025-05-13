package com.aacfahim.streaming_platform.service.impl;

import com.aacfahim.streaming_platform.model.dto.RegisterRqDTO;
import com.aacfahim.streaming_platform.security.model.entity.User;
import com.aacfahim.streaming_platform.security.respository.UserRepository;
import com.aacfahim.streaming_platform.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(RegisterRqDTO dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();

        return userRepository.save(user);
    }
}
