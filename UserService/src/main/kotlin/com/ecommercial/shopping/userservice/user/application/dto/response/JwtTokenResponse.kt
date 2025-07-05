package com.ecommercial.shopping.userservice.user.application.dto.response

class JwtTokenResponse(
    val accessToken: String,
    val refreshToken: String
) {
}