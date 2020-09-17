package com.creditApp.service;

import com.creditApp.model.dto.RegisterDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void save(RegisterDto userDto);

}
