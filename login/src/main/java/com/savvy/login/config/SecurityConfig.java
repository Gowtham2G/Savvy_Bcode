package com.savvy.login.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
            		.requestMatchers("/api/register", "/api/register/**", "/api/login", "/api/login/**").permitAll()
            		 .requestMatchers("/v3/api-docs/**","/swagger-ui/**","/swagger-ui.html").permitAll() //swagger
                .anyRequest().authenticated()
            )
        .formLogin(login -> login.disable()) // Disable form login
        .httpBasic(basic -> basic.disable());
        return http.build();
    }
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
