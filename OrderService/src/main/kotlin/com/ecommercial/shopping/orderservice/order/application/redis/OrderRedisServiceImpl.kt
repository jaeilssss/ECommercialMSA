package com.ecommercial.shopping.orderservice.order.application.redis

import com.ecommercial.shopping.model.UpdateProductCacheMessage
import com.ecommercial.shopping.orderservice.order.application.dto.ProductCacheData
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class OrderRedisServiceImpl(
    val redisTemplate: RedisTemplate<String, String>,
    val objectMapper: ObjectMapper
): OrderRedisService {
    override fun getProductInfo(productId: Long): ProductCacheData? {
        val key = "product:${productId}"
        val cacheData = redisTemplate.opsForValue().get(key)
        if(cacheData == null) {
            return null;
        }
        val productCacheData= objectMapper.readValue(cacheData, ProductCacheData::class.java)


        return productCacheData
    }
}