package com.opencode.alumxbackend.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF only because we're using stateless JWT-style auth (no sessions)
                .csrf(AbstractHttpConfigurer::disable)
                // Stateless session - no session cookies, each request must be authenticated
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints - health check and basic info
                        .requestMatchers("/", "/health", "/actuator/health").permitAll()
                        // Public endpoints - user registration
                        .requestMatchers("/api/users").permitAll()
                        // Public endpoints - mentor basics
                        .requestMatchers("/basics/**").permitAll()
                        // All other API endpoints require authentication
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
