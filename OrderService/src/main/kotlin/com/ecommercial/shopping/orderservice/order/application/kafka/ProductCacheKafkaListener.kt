package com.ecommercial.shopping.orderservice.order.application.kafka

import com.ecommercial.shopping.model.UpdateProductCacheMessage
import com.fasterxml.jackson.databind.ObjectMapper
import lombok.AllArgsConstructor
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.time.Duration

@Component
@AllArgsConstructor
class ProductCacheKafkaListener(
    val redisTemplate: RedisTemplate<String, String>,
    val objectMapper: ObjectMapper
) {

    @KafkaListener(topics = arrayOf("new-orderservice-caching"))
    fun updateProductInfoCache(message: UpdateProductCacheMessage) {
        val key = "product:${message.productId}"
        val json = objectMapper.writeValueAsString(message)

        redisTemplate.opsForValue().set(key, json, Duration.ofDays(3))
    }
}