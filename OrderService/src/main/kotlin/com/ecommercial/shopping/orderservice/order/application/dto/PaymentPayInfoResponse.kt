package com.ecommercial.shopping.orderservice.order.application.dto

import com.ecommercial.shopping.orderservice.global.enums.PaymentType
import com.ecommercial.shopping.orderservice.order.domain.entity.BankTransferPayment
import com.ecommercial.shopping.orderservice.order.domain.entity.CreditPayment

class PaymentPayInfoResponse(
    var accountNumber: String?,
    var bankName: String?,
    var cardNumber: String?,
    var cardCompany: String?,
    var installment: Int?,
) {

    companion object {
        fun create(
            creditPayment: CreditPayment?,
            bankTransferPayment: BankTransferPayment?
        ): PaymentPayInfoResponse {
            val paymentInfoResponse = PaymentPayInfoResponse(
                null,null,null,null,null
            )
            if(creditPayment != null) {
                paymentInfoResponse.cardCompany = creditPayment.cardCompany
                paymentInfoResponse.cardNumber = creditPayment.cardNumber
                paymentInfoResponse.installment = creditPayment.installment
            } else if(bankTransferPayment != null) {
                paymentInfoResponse.bankName = bankTransferPayment.bankName
                paymentInfoResponse.accountNumber = bankTransferPayment.accountNumber
            }else {
                throw Exception()
            }

            return paymentInfoResponse
        }
    }

}