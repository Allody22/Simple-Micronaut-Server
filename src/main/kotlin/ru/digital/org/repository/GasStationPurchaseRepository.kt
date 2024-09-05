
package ru.digital.org.repository

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import ru.digital.org.model.GasStationPurchase

@JdbcRepository(dialect = Dialect.POSTGRES)
interface GasStationPurchaseRepository : CrudRepository<GasStationPurchase, Long> {

    fun getByUser(userId: Long?): List<GasStationPurchase>

}