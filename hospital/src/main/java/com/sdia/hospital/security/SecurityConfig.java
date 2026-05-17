package com.sdia.hospital.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder) {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin").password(passwordEncoder.encode("admin")).roles("ADMIN").build(),
                User.withUsername("amine").password(passwordEncoder.encode("1234")).roles("USER").build(),
                User.withUsername("guest").password(passwordEncoder.encode("1234")).roles("USER").build()
        );
    }

    // filter : intercepter les requetes
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

           httpSecurity
           .formLogin
                   ( form -> form.loginPage("/login")
                           .defaultSuccessUrl("/index", true)
                           .permitAll()
                   )
           .authorizeHttpRequests
                   (
                    auth ->
                            auth.requestMatchers(  "/admin/**")
                                .hasRole("ADMIN")
                                .anyRequest().authenticated()
                    )

           .exceptionHandling( ex ->
                    ex.accessDeniedPage("/notAuthorized"));


        return httpSecurity.build();
    }
}
