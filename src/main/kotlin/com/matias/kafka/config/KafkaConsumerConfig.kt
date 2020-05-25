package com.matias.kafka.config

import com.matias.kafka.model.Order
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer


@EnableKafka
@Configuration
class KafkaConsumerConfig(@Value(value = "\${kafka.bootstrapAddress}") private val bootstrapAddress: String) {

    @Bean
    fun stringConsumerFactory(): ConsumerFactory<String, String> {
        val props: MutableMap<String, Any> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        props[ConsumerConfig.GROUP_ID_CONFIG] = "group_string"
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        return DefaultKafkaConsumerFactory(props)
    }

    @Bean
    fun stringKafkaListenerFactory(): ConcurrentKafkaListenerContainerFactory<String, String> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, String>()
        factory.consumerFactory = stringConsumerFactory()
        return factory
    }

    @Bean
    fun orderConsumerFactory(): ConsumerFactory<String, Order> {
        val props: MutableMap<String, Any> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        props[ConsumerConfig.GROUP_ID_CONFIG] = "group_order"
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
        return DefaultKafkaConsumerFactory(props, StringDeserializer(), JsonDeserializer(Order::class.java))
    }

    @Bean
    fun orderKafkaListenerFactory(): ConcurrentKafkaListenerContainerFactory<String, Order> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Order>()
        factory.consumerFactory = orderConsumerFactory()
        return factory
    }
}