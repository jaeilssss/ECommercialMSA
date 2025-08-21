package com.ecommercial.shopping.userservice.user.application.redis

import com.ecommercial.shopping.userservice.global.jwt.JwtProperties
import com.ecommercial.shopping.userservice.global.jwt.JwtProviders
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class UserRedisServiceImpl(
    val redisTemplate: RedisTemplate<String, Any>
) : UserRedisService {
    override fun isCheckBlackList(accessToken: String): Boolean =
        redisTemplate.hasKey("blacklist: $accessToken") == true

    override fun addBlackList(accessToken: String, expiration: Long) {
        val now = System.currentTimeMillis()
        val ttl = expiration - now
        redisTemplate.opsForValue().set("blacklist: $accessToken",true, ttl, TimeUnit.MILLISECONDS)
    }
}