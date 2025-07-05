package com.ecommercial.shopping.orderservice.global.config

import com.ecommercial.shopping.orderservice.global.annotation.DisableSwaggerSecurity
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springdoc.core.customizers.OperationCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun customize(): OperationCustomizer {
        return OperationCustomizer { operation, handlerMethod ->
            handlerMethod.getMethodAnnotation(DisableSwaggerSecurity::class.java)?.let {
                // DisableSwaggerSecurity 어노테이션이 있을 시 Swagger 보안 설정 제거
                operation.security = emptyList()
            }
            operation
        }
    }
    @Bean
    fun customOpenAPI(): OpenAPI {
        // JWT 인증이 필요한 기본 전역 보안 설정
        val securityRequirement = SecurityRequirement().addList("BearerAuth")

        return OpenAPI()
            .info(
                Info()
                    .title("My API")
                    .version("1.0")
            )
            .components(
                io.swagger.v3.oas.models.Components()
                    .addSecuritySchemes(
                        "BearerAuth",
                        SecurityScheme()
                            .name("BearerAuth")
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")
                    )
            )
            .apply {
                // ✅ 특정 API(`/user/login`, `/user/signup`)는 보안 적용 안 함
                servers(emptyList())
                security(listOf(securityRequirement))
            }
    }
}