package com.ecommercial.shopping.orderservice.order.application

import com.ecommercial.shopping.orderservice.global.dto.BaseResponse
import com.ecommercial.shopping.orderservice.global.error.OrderErrorEnum
import com.ecommercial.shopping.orderservice.global.error.ProductErrorEnum
import com.ecommercial.shopping.orderservice.global.exception.MyException
import com.ecommercial.shopping.orderservice.order.application.dto.OrderInfoResponse
import com.ecommercial.shopping.orderservice.order.application.dto.OrderRequest
import com.ecommercial.shopping.orderservice.order.application.dto.ProductCacheData
import com.ecommercial.shopping.orderservice.order.application.redis.OrderRedisService
import com.ecommercial.shopping.orderservice.order.domain.entity.Order
import com.ecommercial.shopping.orderservice.order.domain.repository.OrderQueryRepository
import com.ecommercial.shopping.orderservice.order.domain.repository.OrderRepository
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
@Transactional(readOnly = true)
class OrderServiceImpl(
    val orderRepository: OrderRepository,
    val orderQueryRepository: OrderQueryRepository,
    val redisService: OrderRedisService,
    val webClient: WebClient
): OrderService {
    @Transactional
    override fun order(request: OrderRequest) {
        val orderItemDataList = ArrayList<ProductCacheData>()

        for(orderItemId in request.itemList) {
            var data = redisService.getProductInfo(orderItemId)

            if (data == null) {
                data = requestHttpProductInfo(orderItemId)?.data
                if (data == null) {
                    throw MyException(
                        ProductErrorEnum.NOT_FOUND_PRODUCT_ID.httpStatus,
                        ProductErrorEnum.NOT_FOUND_PRODUCT_ID.message
                    )
                }
            }

            orderItemDataList.add(data)
        }

        val totalPrice = orderItemDataList.sumOf { it.price }

        val orderItemList = orderItemDataList.stream()
            .map { it.toEntity() }
            .toList()

        val order = request.toEntity(totalPrice, orderItemList)

        orderItemList.stream().forEach { it.order = order }

        orderRepository.save(order)
    }

    override fun getOrderById(orderId: Long): Order {
        return orderQueryRepository.findById(orderId)
            .orElseThrow {
                MyException(
                    OrderErrorEnum.NOT_FOUND_ORDER_ID.httpStatus,
                    OrderErrorEnum.NOT_FOUND_ORDER_ID.message
                )
            }
    }

    override fun getOrderList(userId: Long): List<OrderInfoResponse> {
        return orderQueryRepository.findOrderListByUserId(userId)
            .stream()
            .map { OrderInfoResponse.create(it)}
            .toList()
    }


    private fun requestHttpProductInfo(productId: Long): BaseResponse<ProductCacheData>? {
        try {
            val result = webClient.get()
                    .uri("/get/product/info/$productId")
                    .retrieve()
                    .onStatus(
                    { status -> status.is4xxClientError || status.is5xxServerError }
                    ) { response ->

                        response.bodyToMono(BaseResponse::class.java).flatMap { errorBody ->
                            println(errorBody.data.toString())
                            Mono.error(MyException(HttpStatus.valueOf(response.statusCode().value()), errorBody.data.toString()))
                        }
                    }
                    .bodyToMono(object : ParameterizedTypeReference<BaseResponse<ProductCacheData>>() {})
                    .block()
            return result
        } catch (e: MyException) {
            throw e
        }
    }
}