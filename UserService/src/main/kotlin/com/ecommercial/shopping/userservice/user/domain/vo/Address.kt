package com.ecommercial.shopping.userservice.user.domain.vo

import jakarta.persistence.Embeddable
import lombok.NoArgsConstructor

@Embeddable
data class Address(
    val city: String = "",
    val firstAddress: String = "",
    val secondAddress: String = "",
    val zipCode: String = ""
) {
}