package com.ecommercial.shopping.orderservice.global.jwt

import com.ecommercial.shopping.orderservice.global.error.JWTErrorEnum
import com.ecommercial.shopping.orderservice.global.exception.MyException
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.InitializingBean
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtProviders(
    val jwtProperties: JwtProperties,
): InitializingBean {
    private var key: Key? = null
    override fun afterPropertiesSet() {
        val keyBytes = Decoders.BASE64.decode(jwtProperties.secretKey.replace(" ", ""))
        key = Keys.hmacShaKeyFor(keyBytes)
    }

    private fun getKey(secretKey: String) : SecretKey {
        val keyBytes = Decoders.BASE64.decode(secretKey.replace(" ", ""))
        return Keys.hmacShaKeyFor(keyBytes)
    }
    fun validateToken(token: String): Boolean {
        try {
//            userRedisService.addBlackList(token)
            Jwts.parserBuilder().setSigningKey(getKey(jwtProperties.secretKey)).build().parseClaimsJws(token)
            return true
        } catch (e: Exception) {
            when(e) {
                is io.jsonwebtoken.security.SecurityException, is MalformedJwtException ->{
                    println("Invalid JWT Token")
                    throw MyException(
                        JWTErrorEnum.INVALID_JWT_EXCEPTION.httpStatus,
                        JWTErrorEnum.INVALID_JWT_EXCEPTION.message
                    )
                }
                is ExpiredJwtException -> {
                    println("Expired JWT token")
                    throw MyException(
                        JWTErrorEnum.EXPIRED_JWT_EXCEPTION.httpStatus,
                        JWTErrorEnum.EXPIRED_JWT_EXCEPTION.message
                    )
                }
                is UnsupportedJwtException -> {
                    println("UnsupportedJwtException")
                    throw MyException(
                        JWTErrorEnum.UNSUPPORTED_JWT_EXCEPTION.httpStatus,
                        JWTErrorEnum.UNSUPPORTED_JWT_EXCEPTION.message
                    )
                }
                is IllegalArgumentException -> {
                    println("JWT Claims string is empty")
                    throw MyException(
                        JWTErrorEnum.ILLEGAL_JWT_EXCEPTION.httpStatus,
                        JWTErrorEnum.ILLEGAL_JWT_EXCEPTION.message
                    )
                }
            }
        }
        return false
    }

    fun getAuthentication(accessToken: String): Authentication {
        val claims = parse(accessToken)

        if(claims["userId"] == null) {
            throw RuntimeException("권한 정보가 없는 토큰입니다.")
        }

        val authorities: Collection<GrantedAuthority> = claims["userId"]
            .toString()
            .split(",")
            .map(::SimpleGrantedAuthority)

        return UsernamePasswordAuthenticationToken(claims["userId"], "", authorities)
    }

    fun getUserIdByToken(token: String): Long {
        val claims = parse(token)
        if(claims["userId"] == null) {
            throw RuntimeException("권한 정보가 없는 토큰 입니다.")
        }

        return claims["userId"].toString().toLong()
    }
    fun parse(token: String): Claims {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(getKey(jwtProperties.secretKey))
                .build()
                .parseClaimsJws(token)
                .body
        } catch (e: ExpiredJwtException) {
            e.claims
        }
    }

    fun getExpirationFromToken(token: String): Long = parse(token).expiration.time
}