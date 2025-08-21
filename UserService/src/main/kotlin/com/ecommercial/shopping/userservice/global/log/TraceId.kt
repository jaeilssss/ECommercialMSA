package com.ecommercial.shopping.userservice.global.log

import lombok.Getter
import lombok.Setter
import java.util.UUID

class TraceId {

    val id: String
    var level: Int
    var start : Long
    constructor() {
        this.id = createId()
        this.level = 0
        this.start = 0L
    }

    private fun createId(): String {
        return UUID.randomUUID().toString().substring(0,8)
    }
    
    fun next() {
        level +=1

    }

    fun back() {
        level -= 1
    }


}