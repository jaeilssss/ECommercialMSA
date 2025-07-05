package com.ecommercial.shopping.userservice.user.application.dto.request

import com.ecommercial.shopping.userservice.user.domain.vo.Address

class SignUpRequest(
    val name: String,
    val email: String,
    val birthDay: String,
    val password: String,
    val address: Address,
    val phoneNumber: String
) {
}