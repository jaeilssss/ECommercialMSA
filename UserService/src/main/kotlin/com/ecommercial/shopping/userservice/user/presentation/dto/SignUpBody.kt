package com.ecommercial.shopping.userservice.user.presentation.dto

import com.ecommercial.shopping.userservice.user.application.dto.request.SignUpRequest
import com.ecommercial.shopping.userservice.user.domain.vo.Address


class SignUpBody(
    private val name: String,
    private val email: String,
    private val birthDay: String,
    private val password: String,
    private val address: Address,
    private val phoneNumber: String
) {
    fun toDto() : SignUpRequest = SignUpRequest(
        name = name,
        email = email,
        birthDay = birthDay,
        password = password,
        address = address,
        phoneNumber = phoneNumber
    )
}