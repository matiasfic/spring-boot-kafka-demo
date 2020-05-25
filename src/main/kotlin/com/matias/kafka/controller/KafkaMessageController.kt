package com.matias.kafka.controller

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("message")
class KafkaMessageController(private val kafkaTemplate: KafkaTemplate<String, String>) {

    @GetMapping("{message}")
    fun sendMessage(@PathVariable("message") message: String) {
        kafkaTemplate.send("messageTopic", message)
    }

}