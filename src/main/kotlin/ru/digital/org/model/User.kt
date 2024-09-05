
package ru.digital.org.model

import com.fasterxml.jackson.annotation.JsonIgnore
import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity

@MappedEntity(value = "user")
@Introspected
data class User (

    @field:GeneratedValue(GeneratedValue.Type.AUTO)
    @field:Id var id: Long?,

    //TODO добавить уникальность этого поля
    var username: String?,

    @field:JsonIgnore
    var password: String?,
)