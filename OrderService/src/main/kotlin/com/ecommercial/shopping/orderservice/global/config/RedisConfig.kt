package com.ecommercial.shopping.orderservice.global.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig {


    @Value("\${spring.data.redis.host}")
    private lateinit var host: String

    @Value("\${spring.data.redis.port}")
    private var port: Int = 0


    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory(host, port)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, String> {
        val template = RedisTemplate<String, String>()
        template.connectionFactory = redisConnectionFactory()

        val stringSerializer = StringRedisSerializer()
        val jsonSerializer = GenericJackson2JsonRedisSerializer()

        template.keySerializer = stringSerializer
        template.valueSerializer = jsonSerializer  // 🔥 여기 핵심
        template.hashKeySerializer = stringSerializer
        template.hashValueSerializer = jsonSerializer
        return template
    }

    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
            .registerModule(kotlinModule())  // ✅ 이것이 최신 방식!
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    }

}