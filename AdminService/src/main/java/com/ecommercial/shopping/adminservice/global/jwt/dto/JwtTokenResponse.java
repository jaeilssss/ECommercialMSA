package com.ecommercial.shopping.adminservice.global.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtTokenResponse {
    private final String accessToken;
    private final String refreshToken;
}
