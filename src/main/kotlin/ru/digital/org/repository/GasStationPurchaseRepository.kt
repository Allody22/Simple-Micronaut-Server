
package ru.digital.org.repository

import io.micronaut.data.annotation.Query
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import ru.digital.org.model.GasStationPurchase

@JdbcRepository(dialect = Dialect.POSTGRES)
interface GasStationPurchaseRepository : CrudRepository<GasStationPurchase, Long> {

    fun getByUser(userId: Long?): List<GasStationPurchase>

    @Query(value = "select * from gas_station_purchase WHERE gas_station_purchase.user = :userId " +
            "LIMIT :limit OFFSET :offset", nativeQuery = true)
    fun getByUserPageable(userId: Long?, limit: Int, offset: Int): List<GasStationPurchase>

}