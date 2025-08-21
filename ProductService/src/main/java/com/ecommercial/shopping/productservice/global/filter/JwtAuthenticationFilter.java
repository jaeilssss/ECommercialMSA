package com.ecommercial.shopping.productservice.global.filter;

import com.ecommercial.shopping.productservice.global.jwt.JwtValidation;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtValidation jwtValidation;

    public JwtAuthenticationFilter(JwtValidation jwtValidation) {
        this.jwtValidation = jwtValidation;
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {
        String token = resolveToken((HttpServletRequest) servletRequest);
        if(token != null && jwtValidation.validateToken(token)) {
            Authentication authentication = jwtValidation.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) &&
                bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }



}
