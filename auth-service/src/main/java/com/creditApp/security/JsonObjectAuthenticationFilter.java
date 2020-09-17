package com.creditApp.security;

import com.creditApp.model.User;
import com.creditApp.model.dto.LoginDto;
import com.creditApp.model.dto.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
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

    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;
    private final JwtGenerator jwtGenerator;
    private final ModelMapper modelMapper;

    public JsonObjectAuthenticationFilter(AuthenticationManager authenticationManager,
                                          @Value("${jwt.expirationTime}") int expirationTime,
                                          @Value("${jwt.secret}") String secret) {
        this.authenticationManager = authenticationManager;
        this.objectMapper = new ObjectMapper();
        this.jwtGenerator = new JwtGenerator(expirationTime, secret);
        this.modelMapper = new ModelMapper();
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
        response.addHeader(TOKEN_HEADER, TOKEN_PREFIX + token);
        addUserDataIntoBody(response, user, token);
    }

    private void addUserDataIntoBody(HttpServletResponse response, User user, String token) throws IOException {
        ResponseDTO userResponseDTO = modelMapper.map(user, ResponseDTO.class);
        userResponseDTO.setToken(TOKEN_PREFIX + token);
        objectMapper.writeValueAsString(userResponseDTO);
        response.getWriter().write(objectMapper.writeValueAsString(userResponseDTO));
    }

}
