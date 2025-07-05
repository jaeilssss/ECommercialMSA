package com.ecommercial.shopping.orderservice.order.domain.repository

import com.ecommercial.shopping.orderservice.order.domain.entity.BankTransferPayment
import java.util.Optional

interface BankTransferRepository {

    fun save(bankTransferPayment: BankTransferPayment): Optional<BankTransferPayment>
}