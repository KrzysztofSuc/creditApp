package com.creditApp.service;

import com.creditApp.model.dto.RegisterDto;
import com.creditApp.model.dto.ResponseDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    ResponseDTO save (RegisterDto userDto);

}
