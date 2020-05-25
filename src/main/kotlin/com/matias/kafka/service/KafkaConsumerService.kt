package com.matias.kafka.service

import com.matias.kafka.model.Order
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaConsumerService {

    @KafkaListener(topics = ["messageTopic"], groupId = "group_string", containerFactory = "stringKafkaListenerFactory")
    fun listenMessages(message: String) {
        println("Received message: $message")
    }

    @KafkaListener(topics = ["orderTopic"], groupId = "group_order", containerFactory = "orderKafkaListenerFactory")
    fun listenOrders(order: Order) {
        println("Received order: $order")
    }

}