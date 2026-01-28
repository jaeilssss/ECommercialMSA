package com.ecommercial.shopping.orderservice.global.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer
import org.springframework.kafka.listener.DefaultErrorHandler
import org.springframework.util.backoff.FixedBackOff

@Configuration
class KafkaConfig {

    @Bean
    fun kafkaListnenerContainerFactory(
        consumerFactory: ConsumerFactory<String, String>,
        kafkaTemplate: KafkaTemplate<String, String>
    ): ConcurrentKafkaListenerContainerFactory<String, String> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, String>()
        factory.consumerFactory = consumerFactory

        val recoverer = DeadLetterPublishingRecoverer(kafkaTemplate)
        val backOff = FixedBackOff(1000L, 3)

        val errorHandler = DefaultErrorHandler(recoverer, backOff)

        factory.setCommonErrorHandler(errorHandler)

        return factory
    }
}