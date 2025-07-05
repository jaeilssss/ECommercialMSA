package com.ecommercial.shopping.orderservice.global.config

import com.ecommercial.shopping.orderservice.global.jwt.JwtProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(JwtProperties::class)
class JwtConfig {
}