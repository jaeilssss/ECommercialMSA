package com.ecommercial.shopping.orderservice.global.error

import org.springframework.http.HttpStatus

enum class ProductErrorEnum(
    val httpStatus: HttpStatus,
    val message: String
) {
    NOT_FOUND_PRODUCT_ID(HttpStatus.NOT_FOUND, "주문을 진행 중 에러가 발생 했습니다 (상품 ID 에러)")
}