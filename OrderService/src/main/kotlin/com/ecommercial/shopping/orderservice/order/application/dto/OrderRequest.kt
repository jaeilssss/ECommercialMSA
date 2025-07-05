package com.ecommercial.shopping.orderservice.order.application.dto

import com.ecommercial.shopping.orderservice.order.domain.dto.OrderItemDto
import com.ecommercial.shopping.orderservice.order.domain.entity.Order
import com.ecommercial.shopping.orderservice.order.domain.entity.OrderItem
import jakarta.persistence.GeneratedValue


class OrderRequest(
    val itemList : List<Long>,
    val userId: Long
) {

    fun toEntity(totalPrice: Int, orderItems: List<OrderItem>): Order {
        val order = Order(
            userId = userId,
            totalPrice =  totalPrice,
            orderItems = orderItems
        )
        println(order.id)
        return order
    }
}