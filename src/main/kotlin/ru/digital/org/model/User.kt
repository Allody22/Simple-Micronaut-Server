
package ru.digital.org.model

import com.fasterxml.jackson.annotation.JsonIgnore
import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.Index
import io.micronaut.data.annotation.MappedEntity

@MappedEntity(value = "user")
@Introspected
@Index(name = "username", columns = ["username"], unique = true)
data class User (

    @field:GeneratedValue(GeneratedValue.Type.AUTO)
    @field:Id var id: Long?,

    var username: String?,

    @field:JsonIgnore
    var password: String?,
)