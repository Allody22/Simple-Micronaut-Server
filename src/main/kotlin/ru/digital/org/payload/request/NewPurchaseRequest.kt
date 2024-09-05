package ru.digital.org.payload.request

import io.micronaut.serde.annotation.Serdeable
import java.time.LocalDate

@Serdeable
data class NewPurchaseRequest(
    val date: LocalDate = LocalDate.now(),

    val productName: String?,

    val price: Double?,

    val category: String,

    val userName: String?,
)