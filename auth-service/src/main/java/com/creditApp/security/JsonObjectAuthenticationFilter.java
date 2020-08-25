package com.creditApp.security;

import com.creditApp.model.User;
import com.creditApp.model.dto.LoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonObjectAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private ObjectMapper objectMapper;
    private JwtGenerator jwtGenerator;

    public JsonObjectAuthenticationFilter(AuthenticationManager authenticationManager,
                                          @Value("${jwt.expirationTime}") int expirationTime,
                                          @Value("${jwt.secret}") String secret) {
        this.authenticationManager = authenticationManager;
        this.objectMapper = new ObjectMapper();
        this.jwtGenerator = new JwtGenerator(expirationTime, secret);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(),
                    loginDto.getPassword()
            );
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        String token = jwtGenerator.generateToken(user);
        response.addHeader("Authorization", "Bearer " + token);
    }

}
