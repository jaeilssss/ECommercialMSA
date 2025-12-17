package com.ecommercial.shopping.adminservice.admin.application;

import com.ecommercial.shopping.adminservice.global.jwt.JwtProviders;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class BlackListServiceImpl implements BlackListService{
    private final RedisTemplate<String, String> redisTemplate;
    private final JwtProviders jwtProviders;

    @Override
    public boolean isBlackListToken(String token) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(jwtProviders.makeBlacklistKey(token)));
    }

    @Override
    public void save(String token, long expirationTime) {
        redisTemplate.opsForValue().set(jwtProviders.makeBlacklistKey(token), "logout", expirationTime, TimeUnit.MILLISECONDS);;
    }
}
