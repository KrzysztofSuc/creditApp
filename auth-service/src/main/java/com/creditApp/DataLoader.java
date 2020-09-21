package com.creditApp;

import com.creditApp.model.dto.RegisterDto;
import com.creditApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {
    private final UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userService.save(new RegisterDto("user@wp.pl", "user"));
    }
}
