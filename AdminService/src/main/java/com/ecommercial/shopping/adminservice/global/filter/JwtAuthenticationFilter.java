package com.ecommercial.shopping.adminservice.global.filter;

import com.ecommercial.shopping.adminservice.admin.application.BlackListService;
import com.ecommercial.shopping.adminservice.global.jwt.JwtProviders;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProviders jwtProviders;
    private final BlackListService blacklistService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String token = jwtProviders.resolveToken(request);

        if (token != null) {

            // 1️⃣ 블랙리스트 체크
            if (blacklistService.isBlackListToken(token)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            // 2️⃣ 토큰 검증
            if (jwtProviders.validationToken(token)) {
                Authentication auth = jwtProviders.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }
}