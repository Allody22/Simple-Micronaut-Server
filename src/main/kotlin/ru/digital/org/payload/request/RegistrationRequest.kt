package ru.digital.org.payload.request

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class RegistrationRequest(

    val name: String?,

    val password: String?
)