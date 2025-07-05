package com.ecommercial.shopping.orderservice.order.presentation.dto

import com.ecommercial.shopping.orderservice.order.application.dto.OrderRequest
import com.ecommercial.shopping.orderservice.order.domain.dto.OrderItemDto

class OrderBody(
    val itemList : List<Long>,
) {

    fun toDto(userId: Long): OrderRequest {
        return OrderRequest(
            itemList = itemList,
            userId = userId
        )
    }
}