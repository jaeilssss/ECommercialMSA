package com.ecommercial.shopping.userservice.user.application

import com.ecommercial.shopping.userservice.user.application.dto.request.LoginRequest
import com.ecommercial.shopping.userservice.user.application.dto.request.SignUpRequest
import com.ecommercial.shopping.userservice.user.application.dto.response.JwtTokenResponse
import com.ecommercial.shopping.userservice.user.application.dto.response.UserInfoResponse

interface UserService {

    fun signUp(request: SignUpRequest)
    fun login(request: LoginRequest): JwtTokenResponse

    fun getMyInfo(userId: Long):UserInfoResponse
    fun logout(accessToken: String)
}