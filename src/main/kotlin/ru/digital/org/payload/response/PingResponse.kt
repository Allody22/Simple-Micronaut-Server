package ru.digital.org.payload.response

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class PingResponse (
    val message : String = "welcome message",
)