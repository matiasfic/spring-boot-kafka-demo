package com.matias.kafka.controller

import com.matias.kafka.model.Order
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("orders")
class KafkaOrderController(private val kafkaTemplate: KafkaTemplate<String, Order>) {

    @PostMapping
    fun create(@RequestBody order: Order) {
        kafkaTemplate.send("orderTopic", order)
    }

}