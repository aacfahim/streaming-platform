package com.aacfahim.streaming_platform.service;

import com.aacfahim.streaming_platform.model.dto.RegisterRqDTO;
import com.aacfahim.streaming_platform.security.model.entity.User;

public interface IUserService {

    User register(RegisterRqDTO requestDto);
}
