package com.ecommercial.shopping.orderservice.order.application

import com.ecommercial.shopping.orderservice.global.enums.PaymentStatus
import com.ecommercial.shopping.orderservice.global.error.OrderErrorEnum
import com.ecommercial.shopping.orderservice.global.error.PaymentErrorEnum
import com.ecommercial.shopping.orderservice.global.exception.MyException
import com.ecommercial.shopping.orderservice.order.application.dto.PayRequest
import com.ecommercial.shopping.orderservice.order.application.dto.PaymentInfoResponse
import com.ecommercial.shopping.orderservice.order.domain.entity.Order
import com.ecommercial.shopping.orderservice.order.domain.entity.Payment
import com.ecommercial.shopping.orderservice.order.domain.repository.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BankTransferServiceImpl(
    val bankTransferRepository: BankTransferRepository,
    val bankTransferQueryRepository: BankTransferQueryRepository,
    val paymentQueryRepository: PaymentQueryRepository,
    val paymentRepository: PaymentRepository,
    val orderService: OrderService
) : PaymentService {

    @Transactional
    override fun pay(request: PayRequest) {
        val order = orderService.getOrderById(request.orderId)

        val payment = request.toPayment(order)

        paymentRepository.save(payment)

        bankTransferRepository.save(payment.bankTransferPayment!!)

    }

    override fun getPaymentInfo(paymentId: Long): PaymentInfoResponse {
        val payment = getPaymentInfoById(paymentId)

        return PaymentInfoResponse.create(payment)
    }

    private fun getPaymentInfoById(paymentId: Long): Payment {
        return paymentQueryRepository.findByPaymentData(paymentId)
            .orElseThrow {
                MyException(
                    PaymentErrorEnum.NOT_FOUND_PAYMENT_BY_ID.httpStatus,
                    PaymentErrorEnum.NOT_FOUND_PAYMENT_BY_ID.message
                )
            }
    }
}