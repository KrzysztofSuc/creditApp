package com.creditApp.controller;

import com.creditApp.model.dto.LoginDto;
import com.creditApp.model.dto.RegisterDto;
import com.creditApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody RegisterDto registerDto) {
        userService.save(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginDto loginDto) {
    }
}
