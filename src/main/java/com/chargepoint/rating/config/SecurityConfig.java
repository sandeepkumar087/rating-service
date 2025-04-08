package com.chargepoint.rating.config;

import java.security.SecureRandom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.chargepoint.rating.service.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final CustomUserDetailsService customUserDetailsService;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;

    
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /***
     * 
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
//                .requestMatchers(
//                		"/swagger-ui/**",
//                        "/swagger-ui.html",
//                        "/v3/api-docs/**",
//                        "/v3/api-docs",
//                        "/webjars/**",
//                        "/favicon.ico",            // ✅ Allow Favicon
//                        "/swagger-ui/favicon-32x32.png", // ✅ Allow Specific Favicon
//                        "/swagger-resources/**"     // ✅ Required for Swagger Static Files
//                    ).permitAll()
                .requestMatchers("/api/auth/**").permitAll()// Allow registration & login
                .requestMatchers("/api/ratings/**").authenticated() // Secure rating endpoints
                .requestMatchers("/api/stations/**").authenticated()// Secure station endpoints
                .anyRequest().authenticated()
            )
//            .authorizeHttpRequests(auth -> auth
//                    .requestMatchers("/user/**", "/mobileUser/**", "/user-legacy-sync/**", "/v3/api-docs/**").permitAll()
//                    .anyRequest().authenticated()
//                )
            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Use stateless sessions for JWT
                )
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter

        return http.build();
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    
    @Bean
    public SecureRandom secureRandom() {
        return new SecureRandom();
    }
}
