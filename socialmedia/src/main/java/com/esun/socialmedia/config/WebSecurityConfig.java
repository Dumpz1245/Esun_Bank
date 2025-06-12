package com.esun.socialmedia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF for simplicity during development
            .authorizeHttpRequests()
            .requestMatchers("/api/**").permitAll() // Allow register, etc.
            .anyRequest().authenticated() // Lock down everything else
            .and()
            .formLogin().disable(); // Optional: disables Spring's default login page

        return http.build();
    }
}
