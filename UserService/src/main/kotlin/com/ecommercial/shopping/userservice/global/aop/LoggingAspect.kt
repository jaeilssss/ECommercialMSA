package com.ecommercial.shopping.userservice.global.aop

import com.ecommercial.shopping.userservice.global.exception.MyException
import com.ecommercial.shopping.userservice.global.log.LogTrace
import lombok.extern.slf4j.Slf4j
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Aspect
@Component
class LoggingAspect(
    val logTrace: LogTrace
) {

    @Pointcut("execution(* com.ecommercial.shopping.userservice.user..*(..))")
    fun logTargetPointCut() {}
    @Pointcut("execution(* com.ecommercial.shopping..*Controller.*(..))")
    fun executionTimePointCut(){}



    @Around("logTargetPointCut()")
    fun logMethod(jointPoint: ProceedingJoinPoint): Any? {
        val methodName : String = jointPoint.signature.toShortString()

        try {
            logTrace.begin(methodName)

            val result = jointPoint.proceed()

            logTrace.end(methodName)

            return result
        } catch (e: RuntimeException) {
            logTrace.exception(methodName, e.toString())
            throw e
        }
    }
}
