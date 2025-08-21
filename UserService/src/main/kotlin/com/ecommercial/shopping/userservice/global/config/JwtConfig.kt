package com.ecommercial.shopping.userservice.global.config

import com.ecommercial.shopping.userservice.global.jwt.JwtProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(JwtProperties::class)
class JwtConfig {
}