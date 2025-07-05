package com.ecommercial.shopping.orderservice.order.domain.repository

import com.ecommercial.shopping.orderservice.order.domain.entity.CreditPayment
import java.util.Optional

interface CardRepository {

    fun save(cardPayment: CreditPayment): Optional<CreditPayment>
}