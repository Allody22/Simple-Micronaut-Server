package ru.digital.org.payload.request

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class LoginRequest(

    val name: String = "unknown",

    val password: String = "unknown"
)