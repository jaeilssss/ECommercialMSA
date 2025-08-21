package com.ecommercial.shopping.userservice.user.presentation

import com.ecommercial.shopping.userservice.global.controller.BaseController
import com.ecommercial.shopping.userservice.global.dto.BaseResponse
import com.ecommercial.shopping.userservice.global.jwt.JwtProperties
import com.ecommercial.shopping.userservice.global.jwt.JwtProviders
import com.ecommercial.shopping.userservice.user.application.UserService
import com.ecommercial.shopping.userservice.user.application.dto.response.JwtTokenResponse
import com.ecommercial.shopping.userservice.user.application.dto.response.UserInfoResponse
import com.ecommercial.shopping.userservice.user.presentation.dto.LoginBody
import com.ecommercial.shopping.userservice.user.presentation.dto.SignUpBody
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
class UserController(
    val userService: UserService,
    val jwtProperties: JwtProperties,
    val jwtProviders: JwtProviders
) : BaseController() {

    @PostMapping("/signUp")
    fun signUp(@RequestBody signUpBody: SignUpBody) : ResponseEntity<BaseResponse<String>> {
        userService.signUp(signUpBody.toDto())
        return ResponseEntity.ok(BaseResponse("OK","회원가입이 완료 됐습니다."))
    }

    @PostMapping("/login")
    fun login(@RequestBody loginBody: LoginBody) : ResponseEntity<BaseResponse<JwtTokenResponse>> {
        val jwtTokenResponse = userService.login(loginBody.toDto())

        createCookieToInsertRefreshToken(jwtTokenResponse.refreshToken)

        return ResponseEntity.ok(BaseResponse("OK",jwtTokenResponse))
    }

    @GetMapping("/logout")
    fun logout(request: HttpServletRequest): ResponseEntity<BaseResponse<String>> {
        var token : String = request.getHeader("Authorization")
        token = token.substring(7)
        userService.logout(token)
        return ResponseEntity.ok(BaseResponse("OK","로그아웃 완료 했습니다."))
    }

    @GetMapping("/myUserInfo")
    fun myUserInfo(request: HttpServletRequest): ResponseEntity<BaseResponse<UserInfoResponse>> {
        val userInfo = userService.getMyInfo(jwtProviders.getUserIdByToken(request.getHeader("Authorization")))
        return ResponseEntity.ok(BaseResponse("OK",  userInfo))
    }


    private fun createCookieToInsertRefreshToken(refreshToken: String) : Cookie =
        Cookie("refreshToken", refreshToken).apply {
            isHttpOnly = true
            secure = true
            path = "/"
            maxAge = jwtProperties.refreshExpiration.toInt()
        }
}