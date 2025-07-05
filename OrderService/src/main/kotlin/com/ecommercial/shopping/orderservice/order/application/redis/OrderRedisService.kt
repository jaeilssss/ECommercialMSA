package com.ecommercial.shopping.orderservice.order.application.redis

import com.ecommercial.shopping.model.UpdateProductCacheMessage
import com.ecommercial.shopping.orderservice.order.application.dto.ProductCacheData

interface OrderRedisService {
    fun getProductInfo(key: Long): ProductCacheData?
}