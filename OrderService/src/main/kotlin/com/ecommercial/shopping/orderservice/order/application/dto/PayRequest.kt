package com.ecommercial.shopping.orderservice.order.application.dto

import com.ecommercial.shopping.orderservice.global.enums.PaymentStatus
import com.ecommercial.shopping.orderservice.global.enums.PaymentType
import com.ecommercial.shopping.orderservice.order.domain.entity.BankTransferPayment
import com.ecommercial.shopping.orderservice.order.domain.entity.CreditPayment
import com.ecommercial.shopping.orderservice.order.domain.entity.Order
import com.ecommercial.shopping.orderservice.order.domain.entity.Payment
import com.ecommercial.shopping.orderservice.order.domain.vo.BankTransferDto
import com.ecommercial.shopping.orderservice.order.domain.vo.CardPaymentDto
import lombok.Builder

@Builder
class PayRequest(
    val userId: Long,
    val orderId: Long,
    val price: Int,
    val paymentType: PaymentType,
    val cardPayInfo: CardPaymentDto? = null,
    val bankTransferInfo: BankTransferDto? = null,
    val paymentStatus: PaymentStatus

) {

    fun toPayment(order: Order): Payment {
        val creditPayment = toCreditPayment(true)
        val bankTransferPayment = toBankTransferPayment(true)

        return Payment(
            order = order,
            price = price,
            paymentType = paymentType,
            paymentStatus = paymentStatus,
            creditPayment = creditPayment,
            bankTransferPayment = bankTransferPayment,
            userId = userId
        )
    }

    fun toCreditPayment(payResult: Boolean): CreditPayment? {
        if(cardPayInfo != null) {
            return CreditPayment(
                userId = userId,
                cardCompany = cardPayInfo.cardCompany,
                cardNumber = cardPayInfo.cardNumber,
                installment = cardPayInfo.installment,
                status = payResult,
                paymentStatus = paymentStatus
            )
        }
        return null;
    }

    fun toBankTransferPayment(payResult: Boolean): BankTransferPayment? {
        if(bankTransferInfo != null) {
            return BankTransferPayment(
                accountNumber = bankTransferInfo.accountNumber,
                bankName = bankTransferInfo.bankName,
                status = payResult,
                paymentStatus = paymentStatus,
                userId = userId
            )
        }
        return null
    }
}