package com.ecommercial.shopping.userservice.user.presentation.dto

import com.ecommercial.shopping.userservice.user.application.dto.request.LoginRequest

class LoginBody(
    private val email: String,
    private val password: String
) {

    fun toDto(): LoginRequest = LoginRequest(
        email = email,
        password = password
    )
}