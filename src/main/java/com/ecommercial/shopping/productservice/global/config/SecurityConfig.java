package com.ecommercial.shopping.productservice.global.config;

import com.ecommercial.shopping.productservice.global.filter.JwtAuthenticationFilter;
import com.ecommercial.shopping.productservice.global.jwt.JwtValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtValidation jwtValidation;

    @Bean
    public PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .rememberMe(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {
                    request.requestMatchers("/admin/**").hasRole("ADMIN");
                    request.requestMatchers("/user/**").hasRole("USER");
                    request.requestMatchers("/product/register").hasAnyRole("MANAGER", "SUPER_ADMIN");
                    request.anyRequest().permitAll();
                })
                .addFilterBefore(new JwtAuthenticationFilter(jwtValidation), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
