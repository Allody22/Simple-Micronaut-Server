package ru.digital.org.payload.request

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class UserNameRequest (

    val username: String = "unknown"
)