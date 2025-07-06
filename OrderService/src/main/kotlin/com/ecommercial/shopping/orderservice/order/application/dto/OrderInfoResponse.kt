package com.ecommercial.shopping.orderservice.order.application.dto

import com.ecommercial.shopping.orderservice.order.domain.entity.Order

class OrderInfoResponse(
    val totalPrice: Int,
    val orderItems: List<OrderItemsInfoResponse>
) {

    companion object {
        fun create(
            order: Order
        ): OrderInfoResponse {
            return OrderInfoResponse(
                totalPrice = order.totalPrice,
                orderItems = order.orderItems.stream()
                    .map { OrderItemsInfoResponse.create(it) }
                    .toList()
            )
        }
    }
}