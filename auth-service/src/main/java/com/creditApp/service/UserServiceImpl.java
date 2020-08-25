package com.creditApp.service;

import com.creditApp.model.User;
import com.creditApp.model.dto.RegisterDto;
import com.creditApp.model.dto.ResponseDTO;
import com.creditApp.repository.UserRepository;
import com.creditApp.security.JwtGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtGenerator jwtGenerator;

    public UserServiceImpl(ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRepository userRepository, JwtGenerator jwtGenerator) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtGenerator = jwtGenerator;
    }

    @Override
    public ResponseDTO save(RegisterDto registerDto) {
        User user = modelMapper.map(registerDto, User.class);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setEnable(true);
        user.setRole("USER");
        userRepository.save(user);
        ResponseDTO responseDTO = modelMapper.map(user, ResponseDTO.class);
        // responseDTO.setToken(jwtGenerator.generateToken(user));
        return responseDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow();
    }
}
