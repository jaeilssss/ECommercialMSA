package com.ecommercial.shopping.orderservice.global.jwt

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
data class JwtProperties(
    val headers: String,
    val secretKey: String,
    val expiration: Long,
    val refreshExpiration: Long
) {
}