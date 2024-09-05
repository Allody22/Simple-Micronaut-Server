/*
 * Copyright 2017-2024 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.digital.org.model

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import ru.digital.org.model.constants.ECategory
import java.time.LocalDate

//Любая покупка на заправке, то есть и топливо и бутеры и тп
@MappedEntity(value = "gas_station_purchase")
@Serdeable
data class GasStationPurchase(

    //Без этого аннотация еще над сеттерами и геттерами ставится
    @field:GeneratedValue(GeneratedValue.Type.AUTO)
    @field:Id val id: Long?,

    var productName: String? = "Unknown product",

    var price: Double? = 0.0,

    var eCategory: ECategory ? = ECategory.UNKNOWN_CATEGORY,

    var date: LocalDate = LocalDate.now(),

    val user: Long?
)