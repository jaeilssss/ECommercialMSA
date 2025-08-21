package com.ecommercial.shopping.productservice.global.jwt;

import com.ecommercial.shopping.productservice.global.dto.AdminPrincipal;
import com.ecommercial.shopping.productservice.global.dto.UserPrincipal;
import com.ecommercial.shopping.productservice.global.enums.AdminRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.awt.*;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtValidation implements InitializingBean {

    private final String secretKey;
    private Key key;

    public JwtValidation(@Value("${jwt.secretKey}") String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey.replace(" ",""));
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException |
                 MalformedJwtException e) {
            log.info("Invalid JWT token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token", e);
            throw new JwtException("error");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty", e);
        }
        return false;
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        if (claims.get("account-type",String.class).equals("Admin")) {
            return generateAdminAuthToken(claims);
        } else {
            return generateUserAuthToken(claims);
        }

    }

    private UsernamePasswordAuthenticationToken generateAdminAuthToken(Claims claims) {
        AdminRole role = AdminRole.valueOf(claims.get("role", String.class));
        Long adminUserId = claims.get("userId", Long.class);
        Long companyId = claims.get("companyId", Long.class);
        String email = claims.getSubject();
        AdminPrincipal adminPrincipal = new AdminPrincipal(adminUserId, email, role, companyId);

        return new UsernamePasswordAuthenticationToken(
                adminPrincipal,
                "",
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN")
                )
        );
    }

    private UsernamePasswordAuthenticationToken generateUserAuthToken(Claims claims) {
        Long userId = claims.get("userId", Long.class);
        String email = claims.getSubject();
        UserPrincipal userPrincipal = new UserPrincipal(email, userId);

        return new UsernamePasswordAuthenticationToken(
                userPrincipal,
                "",
                List.of(new SimpleGrantedAuthority("ROLE_USER")
                )
        );
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

}
