package ru.digital.org.model

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import ru.digital.org.model.constants.ECategory

@MappedEntity(value = "category_types")
@Serdeable
data class Category (

    @field:GeneratedValue(value = GeneratedValue.Type.IDENTITY)
    @field:Id var id : Long,

    var name: ECategory = ECategory.UNKNOWN_CATEGORY
)