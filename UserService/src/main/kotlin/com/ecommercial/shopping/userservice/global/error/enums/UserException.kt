package com.ecommercial.shopping.userservice.global.error.enums

import org.springframework.http.HttpStatus

enum class UserException(
    val httpStatus: HttpStatus,
    val message: String
) {

    ALREADY_JOIN_EMAIL(HttpStatus.CONFLICT, "이미 가입된 이메일 입니다."),
    IS_NOT_FOUND_USER_BY_EMAIL(HttpStatus.NOT_FOUND, "해당 이메일의 유저를 찾을 수 없습니다."),
    IS_NOT_FOUND_USER_BY_ID(HttpStatus.NOT_FOUND, "유저 정보를 찾을 수 없습니다.")
}