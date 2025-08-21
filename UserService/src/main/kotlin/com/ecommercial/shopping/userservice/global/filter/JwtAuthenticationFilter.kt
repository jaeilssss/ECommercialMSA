package com.ecommercial.shopping.userservice.global.filter

import com.ecommercial.shopping.userservice.global.jwt.JwtProviders
import jakarta.servlet.FilterChain
import jakarta.servlet.GenericFilter
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.StringUtils

class JwtAuthenticationFilter(val jwtProviders: JwtProviders) : GenericFilter() {
    override fun doFilter(
        servletRequest: ServletRequest?,
        servletResponse: ServletResponse?,
        filterChain: FilterChain?
    ) {
        val token: String? = (servletRequest as? HttpServletRequest)?.let { resolveToken(it) }
        println(token)
        if (token != null && jwtProviders.validateToken(token)) {
            val authentication = jwtProviders.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain?.doFilter(servletRequest, servletResponse)
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        val bearToken = request.getHeader("Authorization")
        if (StringUtils.hasText(bearToken) && bearToken.startsWith("Bearer")) {
            return bearToken.substring(7)
        }
        return null
    }
}