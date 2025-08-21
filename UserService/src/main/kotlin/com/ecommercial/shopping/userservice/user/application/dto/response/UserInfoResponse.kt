package com.ecommercial.shopping.userservice.user.application.dto.response

import com.ecommercial.shopping.userservice.global.enums.Grade
import com.ecommercial.shopping.userservice.user.domain.entity.User
import com.ecommercial.shopping.userservice.user.domain.vo.Address

class UserInfoResponse(
    val name: String,
    val email: String,
    val birthDay: String,
    val address: Address,
    val phoneNumber: String,
    val grade: Grade
) {

    companion object {
        fun fromEntity(user: User) : UserInfoResponse = UserInfoResponse(
            name = user.name,
            email = user.email,
            birthDay = user.birthDay,
            address = user.address,
            phoneNumber = user.phoneNumber,
            grade = user.grade
        )
    }

}