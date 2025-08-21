package com.ecommercial.shopping.userservice.global.log

import com.ecommercial.shopping.userservice.global.aop.LoggingAspect
import org.slf4j.LoggerFactory
import org.springdoc.api.ErrorMessage
import org.springframework.boot.autoconfigure.condition.ConditionMessage.Style
import org.springframework.stereotype.Component

@Component
class LogTraceImpl : LogTrace{
    private val traceIdHolder: ThreadLocal<TraceId> = ThreadLocal()
    private val log = LoggerFactory.getLogger(LoggingAspect::class.java)

    private val START_PREFIX : String = "-->"
    private val COMPLETE_PREFIX : String = "<--"
    override fun begin(message: String) {
        syncTraceId()
        if(traceIdHolder.get().level == 1) {
            traceIdHolder.get().start = System.currentTimeMillis()
        }
        val traceId = traceIdHolder.get()
        log.info("{} {} {} [{}]",traceId.id ,START_PREFIX, createBlankOfLevel(), message)
        traceId.next()
    }

    override fun end(message: String) {
        val traceId = traceIdHolder.get()
        log.info("{} {} {} [{}]", traceId.id, COMPLETE_PREFIX,createBlankOfLevel(),message)
        traceId.back()
        if(traceId.level == 0) {
            stopTimeLogging()
            traceIdHolder.remove()
        }
    }

    fun stopTimeLogging() {
        val endTime = System.currentTimeMillis() - traceIdHolder.get().start
        log.info("{} endTime : {}" , traceIdHolder.get().id, endTime)
    }

    override fun exception(message: String, errorMessage: String) {
        log.error("exception {}  {} {}" ,traceIdHolder.get().id, message, errorMessage)
        traceIdHolder.remove()
    }

    override fun timeBegin(start: Long) {
        syncTraceId()
        log.info("{} : start {}", traceIdHolder.get().id, start)
    }

    override fun timeEnd(start : Long) {
        val resultTimes = System.currentTimeMillis() - start
        log.info("{} : end {}" , traceIdHolder.get().id, System.currentTimeMillis())

    }

    fun createBlankOfLevel(): String {
        val builder = StringBuilder()
        val traceId = traceIdHolder.get()
        for(i in 0 until traceId.level) {
            builder.append("  ")
        }
        return builder.toString()
    }

    fun syncTraceId() {
        var traceId : TraceId? = traceIdHolder.get()

        if(traceId == null) {
           traceIdHolder.set(TraceId())
        } else {
            traceIdHolder.set(traceId)
        }
    }

}