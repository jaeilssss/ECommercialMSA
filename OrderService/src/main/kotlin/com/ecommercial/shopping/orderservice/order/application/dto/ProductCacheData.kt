package com.ecommercial.shopping.orderservice.order.application.dto

import com.ecommercial.shopping.orderservice.order.domain.entity.Order
import com.ecommercial.shopping.orderservice.order.domain.entity.OrderItem
import org.apache.logging.log4j.util.StringMap

class ProductCacheData(
    val productId: Long,
    val productName: String,
    val companyName: String?,
    val companyId: Long,
    val categoryId: Long,
    val categoryName: String,
    val price: Int,
    val amount: Int
) {

    fun toEntity(): OrderItem {
        return OrderItem(
            productId = productId,
            productName = productName,
            companyId = companyId,
            price = price,
            categoryId = categoryId,
            categoryName = categoryName,
        )
    }
}