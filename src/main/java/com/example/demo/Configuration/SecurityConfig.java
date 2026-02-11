package com.example.demo.Configuration;

import com.example.demo.SpringSecurityDetail.JwtVerify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    JwtVerify jwtVerify;
    @Autowired
    jwtAccesshandler jwtAccesshandler;
    @Autowired
    JwtAuthentication jwtAuthentication;

    @Bean
   public SecurityFilterChain configureChain(HttpSecurity httpSecurity) throws Exception{
    httpSecurity.csrf((csrf)->{
        csrf.disable();
    })
            .sessionManagement((session)->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling(exception->{
                exception.authenticationEntryPoint(jwtAuthentication)//need to read about it
                        .accessDeniedHandler(jwtAccesshandler);
            })
            .authorizeHttpRequests((request)->{
                request.requestMatchers("/api/auth/register","/api/auth/login","/api/auth/forgot-password").permitAll()
                        .requestMatchers("/login/admin/**").hasRole("ADMIN")
                        .requestMatchers("/login/student/**").hasRole("STUDENT")
                        .anyRequest().authenticated();
            }).addFilterBefore(jwtVerify, UsernamePasswordAuthenticationFilter.class);
       return  httpSecurity.build();


    }
    @Bean
    public AuthenticationManager getAuthentication(AuthenticationConfiguration config){
       return  config.getAuthenticationManager();
    }

}
