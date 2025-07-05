package com.ecommercial.shopping.orderservice.order.application

import com.ecommercial.shopping.orderservice.order.application.dto.OrderRequest
import com.ecommercial.shopping.orderservice.order.domain.entity.Order

interface OrderService {
    fun order(request: OrderRequest)
    fun getOrderById(orderId: Long): Order
}