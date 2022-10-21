package com.example.BankingProject.security;

import com.example.BankingProject.services.users.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

import static com.example.BankingProject.enums.Status.FROZEN;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConf) throws Exception {
        return authConf.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();

        httpSecurity.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/admin/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.PUT, "/admin/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.PATCH, "/admin/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/admin/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET, "/holderAccount/**").hasRole("USER")
                .mvcMatchers(HttpMethod.POST, "/holderAccount/**").hasRole("USER")
                .mvcMatchers(HttpMethod.PUT, "/holderAccount/**").hasRole("USER")
                .mvcMatchers(HttpMethod.PATCH, "/holderAccount/**").hasRole("USER")
                .mvcMatchers(HttpMethod.PATCH, "/thirdParty/**").hasRole("USER")
                .anyRequest().permitAll();

        httpSecurity.csrf().disable();

        return httpSecurity.build();

    }
}
