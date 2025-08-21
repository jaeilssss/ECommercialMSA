package com.ecommercial.shopping.userservice.global.jwt

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@ConfigurationProperties(prefix = "jwt")
data class JwtProperties(
     val headers: String,
     val secretKey: String,
     val expiration: Long,
     val refreshExpiration: Long
) {
}