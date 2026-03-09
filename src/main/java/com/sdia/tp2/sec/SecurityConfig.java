package com.sdia.tp2.sec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean // password Hashing
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password("1234").roles("USER").build(),
                User.withUsername("user2").password("1234").roles("USER").build(),
                User.withUsername("admin").password("1234").roles("USER" , "ADMIN").build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .formLogin(Customizer.withDefaults()) // formulaire par defaut
                .authorizeHttpRequests(ar -> ar.requestMatchers("/index/**").hasRole("USER"))
                .authorizeHttpRequests(ar -> ar.requestMatchers("/save/**" , "/delete/**").hasRole("ADMIN"))
                .authorizeHttpRequests(ar -> ar.anyRequest().authenticated() )
                .build();
    }
}
