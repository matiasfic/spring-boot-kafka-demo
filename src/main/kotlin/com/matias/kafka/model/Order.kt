package com.matias.kafka.model

import java.math.BigDecimal

data class Order(val id: String, val status: String, val price: BigDecimal)