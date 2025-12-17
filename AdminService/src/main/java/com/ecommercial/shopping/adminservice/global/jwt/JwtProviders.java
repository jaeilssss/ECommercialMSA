package com.ecommercial.shopping.adminservice.global.jwt;

import com.ecommercial.shopping.adminservice.admin.domain.entity.Admin;
import com.ecommercial.shopping.adminservice.global.error.JwtError;
import com.ecommercial.shopping.adminservice.global.exception.MyException;
import com.ecommercial.shopping.adminservice.global.jwt.dto.JwtTokenResponse;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.SignatureException;
import java.util.Date;

@Slf4j
@Component
@Getter
@Setter
public class JwtProviders implements InitializingBean {
    @Value("${jwt.headers}")
    public static String headers;

    @Value("${jwt.secretKey}")
    public String secretKey;

    @Value("${jwt.expiration}")
    public Long expiration;

    @Value("${jwt.refresh_expiration}")
    public Long refreshExpiration;

    private Key key;

    @Value("${redis.blacklist_token}")
    private String blacklistPrefix;

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey.replace(" ",""));
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public JwtTokenResponse createToken(Admin admin) {
        Claims claims = Jwts.claims();

        claims.put(headers, admin.getEmail());
        Long now = new Date().getTime();
        Date access_validity = new Date(now + this.expiration);
        Date refresh_validity = new Date(now + this.refreshExpiration);

        String accessToken = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(access_validity)
                .setSubject(admin.getEmail())
                .claim("userId", admin.getId())
                .claim("account-type","Admin")
                .claim("role", admin.getRole())
                .claim("companyId", admin.getCompany().getId())
                .signWith(SignatureAlgorithm.HS256, getKey(secretKey))
                .compact();

        String refreshToken = Jwts.builder()
                .setExpiration(refresh_validity)
                .claim("userId",admin.getId())
                .signWith(SignatureAlgorithm.HS256, getKey(secretKey))
                .compact();

        return new JwtTokenResponse(accessToken, refreshToken);
    }

    public SecretKey getKey(String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey.replace(" ", ""));
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Boolean validationToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getKey(secretKey)).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            if (e instanceof SecurityException || e instanceof SignatureException) {
                System.out.println("Invalid JWT signature.");
                throw new MyException(
                        JwtError.INVALID_JWT_EXCEPTION.getHttpStatus(),
                        JwtError.INVALID_JWT_EXCEPTION.getMessage()
                );
            } else if (e instanceof ExpiredJwtException) {
                throw new MyException(
                        JwtError.EXPIRED_JWT_EXCEPTION.getHttpStatus(),
                        JwtError.EXPIRED_JWT_EXCEPTION.getMessage()
                );
            } else if (e instanceof MalformedJwtException) {
                System.out.println("Malformed JWT token.");
                throw new MyException(
                        JwtError.ILLEGAL_JWT_EXCEPTION.getHttpStatus(),
                        JwtError.ILLEGAL_JWT_EXCEPTION.getMessage()
                );
            } else if (e instanceof UnsupportedJwtException) {
                throw new MyException(
                        JwtError.UNSUPPORTED_JWT_EXCEPTION.getHttpStatus(),
                        JwtError.UNSUPPORTED_JWT_EXCEPTION.getMessage()
                );
            } else {
                throw new MyException(
                        JwtError.ILLEGAL_JWT_EXCEPTION.getHttpStatus(),
                        JwtError.ILLEGAL_JWT_EXCEPTION.getMessage()
                );
            }
        }
    }
    public long getRemainExpirationTime(String token) {
        Date expiration = getTokenExpiration(token);
        return expiration.getTime() - System.currentTimeMillis();
    }
    
    public Date getTokenExpiration(String token) {
        return parseClaims(token).getExpiration();
    }

    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String makeBlacklistKey(String token) {
        return blacklistPrefix + token;
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(headers);

        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            return null;
        }

        return bearerToken.substring("Bearer ".length());
    }
}
