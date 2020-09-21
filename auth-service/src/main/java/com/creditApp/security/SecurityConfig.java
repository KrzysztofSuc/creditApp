package com.creditApp.security;

import com.creditApp.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final int expirationTime;
    private final String secret;
    private final CustomAuthenticationFailureHandler failureHandler;

    public SecurityConfig(PasswordEncoder passwordEncoder, UserService userService,
                          @Value("${jwt.expirationTime}") int expirationTime,
                          @Value("${jwt.secret}") String secret,
                          CustomAuthenticationFailureHandler failureHandler) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.expirationTime = expirationTime;
        this.secret = secret;
        this.failureHandler = failureHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable();
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public JsonObjectAuthenticationFilter authenticationFilter() throws Exception {
        JsonObjectAuthenticationFilter filter = new JsonObjectAuthenticationFilter(expirationTime, secret);
        filter.setAuthenticationFailureHandler(failureHandler);
        filter.setAuthenticationManager(super.authenticationManager());
        return filter;
    }

}
