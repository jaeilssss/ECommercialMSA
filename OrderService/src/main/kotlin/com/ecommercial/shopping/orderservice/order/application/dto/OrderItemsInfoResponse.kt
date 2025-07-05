package com.ecommercial.shopping.orderservice.order.application.dto

import com.ecommercial.shopping.orderservice.order.domain.entity.*

class OrderItemsInfoResponse(
    val productId: Long,
    val productName: String,
    val price: Int,
    val categoryId: Long,
    val categoryName: String
) {

    companion object {
        fun create(orderItem: OrderItem): OrderItemsInfoResponse {
            return OrderItemsInfoResponse(
                productId = orderItem.productId,
                productName = orderItem.productName,
                categoryId = orderItem.categoryId,
                categoryName = orderItem.categoryName,
                price = orderItem.price
            )
        }
    }

}