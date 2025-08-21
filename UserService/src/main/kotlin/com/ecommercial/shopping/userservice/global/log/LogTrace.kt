package com.ecommercial.shopping.userservice.global.log

interface LogTrace {

    fun begin(message: String): Unit

    fun end(message: String): Unit

    fun exception(message: String,errorMessage: String): Unit

    fun timeBegin(start: Long)

    fun timeEnd(start: Long)
}