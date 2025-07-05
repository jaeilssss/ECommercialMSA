package com.ecommercial.shopping.orderservice.global.filter

import com.ecommercial.shopping.orderservice.global.exception.MyException

import com.google.gson.Gson
import com.google.gson.JsonObject
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.web.filter.OncePerRequestFilter

class JwtExceptionFilter: OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        response.characterEncoding = "utf-8"
        try {
            filterChain.doFilter(request, response)
        } catch (e: MyException) {
            setErrorResponse(HttpStatus.UNAUTHORIZED, response, e)
        }
    }
    private fun setErrorResponse(status: HttpStatus, res: HttpServletResponse, e: MyException) {
        res.status = status.value()
        res.contentType = "application/json; charset=UTF-8"
        res.writer.write(tokenExpiredResponseToJson(e.message))
    }

    private fun tokenExpiredResponseToJson(message: String): String {
        val gson = Gson()
        val jsonObject = JsonObject()
        jsonObject.addProperty("code", "Token-Error")
        jsonObject.addProperty("message", message)
        return gson.toJson(jsonObject)
    }
}