package com.ecommercial.shopping.orderservice.order.presentation

import com.ecommercial.shopping.orderservice.global.dto.BaseResponse
import com.ecommercial.shopping.orderservice.global.jwt.JwtProviders
import com.ecommercial.shopping.orderservice.order.application.OrderService
import com.ecommercial.shopping.orderservice.order.application.dto.OrderInfoResponse
import com.ecommercial.shopping.orderservice.order.presentation.dto.OrderBody
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/order")
class OrderController(
    val orderService: OrderService,
    val jwtProviders: JwtProviders
) {

    @PostMapping("/order")
    suspend fun createOrder(@RequestBody body: OrderBody, httpServletRequest: HttpServletRequest): ResponseEntity<BaseResponse<String>> {
        val authHeader = httpServletRequest.getHeader("Authorization")
        val token = authHeader?.removePrefix("Bearer ")?.trim()

        orderService.order(body.toDto(jwtProviders.getUserIdByToken(token!!)))

        return ResponseEntity.ok(BaseResponse("OK","주문 생성 완료"))
    }

    @GetMapping("/order/my/list")
    fun getMyOrderList(httpServletRequest: HttpServletRequest): ResponseEntity<BaseResponse<List<OrderInfoResponse>>> {
        val authHeader = httpServletRequest.getHeader("Authorization")
        val token = authHeader?.removePrefix("Bearer ")?.trim()

        return ResponseEntity.ok(BaseResponse(
            "OK",
            orderService.getOrderList(
                jwtProviders.getUserIdByToken(token!!)
            )
        ))
    }


}