package com.ecommercial.shopping.orderservice.order.domain.dto

import com.ecommercial.shopping.orderservice.order.domain.entity.OrderItem
import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.Getter
import lombok.Setter

@Getter
@Setter

data class OrderItemDto(
    val productId: Long,
    val productName: String,
    val companyId: Long,
    val price: Int,
    val categoryId: Long,
    val categoryName: String
){

    fun toEntity(): OrderItem {
        return OrderItem(
            productId = productId,
            productName = productName,
            companyId = companyId,
            price = price,
            categoryId = categoryId,
            categoryName = categoryName
        )
    }
}