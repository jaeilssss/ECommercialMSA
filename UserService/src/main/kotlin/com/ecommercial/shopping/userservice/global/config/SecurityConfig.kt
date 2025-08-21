package com.ecommercial.shopping.userservice.global.config

import com.ecommercial.shopping.userservice.global.filter.JwtAuthenticationFilter
import com.ecommercial.shopping.userservice.global.filter.JwtExceptionFilter
import com.ecommercial.shopping.userservice.global.jwt.JwtProviders
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(val jwtProviders: JwtProviders) {

    @Bean
    fun passwordEncoder() : PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain =
        httpSecurity
            .csrf {it.disable()}
            .httpBasic { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .rememberMe { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers("/logout ").authenticated()
                it.anyRequest().permitAll()
            }
            .addFilterBefore(JwtAuthenticationFilter(jwtProviders), UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(JwtExceptionFilter(), JwtAuthenticationFilter::class.java)
            .build()
}