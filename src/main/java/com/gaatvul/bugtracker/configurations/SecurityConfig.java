package com.gaatvul.bugtracker.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.gaatvul.bugtracker.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
        .authorizeHttpRequests(authorize -> authorize
        .antMatchers("/signup", "/css/**", "/js/**", "/webjars/**").permitAll()
        .antMatchers("/admin/**").hasRole("Administrator")
        .anyRequest().authenticated())
        .formLogin()
        .and()
        .exceptionHandling()
        .accessDeniedPage("/access-denied");

        return http.build();
    }

    @Bean
    public AuthenticationManager buildAuthenticationManager(HttpSecurity http, UserDetailsServiceImpl userDetailsService)
            throws Exception {

        return http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {

        return NoOpPasswordEncoder.getInstance();
    }
}
