package com.creditApp.security;

import com.creditApp.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;


@Configuration
@EnableWebSecurity(debug = false)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final int expirationTime;
    private final String secret;

    public SecurityConfig(PasswordEncoder passwordEncoder, UserService userService,
                          @Value("${jwt.expirationTime}") int expirationTime,
                          @Value("${jwt.secret}") String secret) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.expirationTime = expirationTime;
        this.secret = secret;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable();
        http
                .authorizeRequests()
                .antMatchers("/save").permitAll()
                .anyRequest().authenticated()
                .and()
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //.and()
                .addFilter(new JsonObjectAuthenticationFilter(authenticationManager(), expirationTime, secret))
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(this.userService)
                .passwordEncoder(passwordEncoder);
    }

}
