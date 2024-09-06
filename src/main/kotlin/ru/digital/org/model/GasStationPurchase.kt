
package ru.digital.org.model

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.MappedProperty
import io.micronaut.serde.annotation.Serdeable
import ru.digital.org.model.constants.ECategory
import java.time.LocalDateTime

//Любая покупка на заправке, то есть и топливо и бутеры и тп
@MappedEntity(value = "gas_station_purchase")
@Serdeable
data class GasStationPurchase(

    //Без этого аннотация еще над сеттерами и геттерами ставится
    @field:GeneratedValue(GeneratedValue.Type.AUTO)
    @field:Id val id: Long?,

    var productName: String? = "Unknown product",

    var price: Double? = 0.0,

    @MappedProperty
    var eCategory: ECategory = ECategory.UNKNOWN_CATEGORY,

    var date: LocalDateTime = LocalDateTime.now(),

    val userId: Long?
)