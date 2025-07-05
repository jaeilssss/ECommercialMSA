package com.ecommercial.shopping.orderservice.global.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class ProductWebClientConfig {

    @Bean
    fun productWebClient(): WebClient {
        return WebClient.builder()
            .baseUrl("http://localhost:8081/product")
            .build()
    }

}