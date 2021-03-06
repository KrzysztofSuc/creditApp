package com.creditApp.security;

import com.creditApp.exception.ErrorDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        ErrorDetails errorDetails = ErrorDetails.builder()
                .details(String.valueOf(exception.getClass()))
                .message(exception.getMessage())
                .timestamp(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss", Locale.ENGLISH)
                        .format(LocalDateTime.now()))
                .build();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(mapper.writeValueAsString(errorDetails));
    }
}
