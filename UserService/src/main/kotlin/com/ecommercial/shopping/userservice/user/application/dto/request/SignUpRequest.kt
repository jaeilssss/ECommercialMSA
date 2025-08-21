package com.ecommercial.shopping.userservice.user.application.dto.request

import com.ecommercial.shopping.userservice.global.enums.Grade
import com.ecommercial.shopping.userservice.user.domain.entity.User
import com.ecommercial.shopping.userservice.user.domain.vo.Address

class SignUpRequest(
    val name: String,
    val email: String,
    val birthDay: String,
    val password: String,
    val address: Address,
    val phoneNumber: String
) {
    public fun toEntity(password: String) = User(
        name = name,
        email = email,
        birthDay = birthDay,
        password = password,
        address = address,
        phoneNumber = phoneNumber,
        grade = Grade.BRONZE,
        isDeleted = false
    )
}