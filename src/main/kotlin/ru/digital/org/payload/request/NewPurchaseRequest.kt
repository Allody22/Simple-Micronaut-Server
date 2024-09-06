package ru.digital.org.payload.request

import io.micronaut.serde.annotation.Serdeable
import java.time.LocalDateTime

@Serdeable
data class NewPurchaseRequest(

    val date: LocalDateTime = LocalDateTime.now(),

    val productName: String = "unknown",

    val price: Double = 0.0,

    val category: String = "unknown",
)