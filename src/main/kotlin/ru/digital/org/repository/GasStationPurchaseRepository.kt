package ru.digital.org.repository

import io.micronaut.data.annotation.Query
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import ru.digital.org.model.GasStationPurchase
import ru.digital.org.model.constants.ECategory
import java.time.LocalDateTime

@JdbcRepository(dialect = Dialect.POSTGRES)
interface GasStationPurchaseRepository : CrudRepository<GasStationPurchase, Long> {

    fun getByUserId(userId: Long?, pageable: Pageable): Page<GasStationPurchase>

    fun getByUserId(userId: Long?): List<GasStationPurchase>

    @Query(value = "INSERT INTO gas_station_purchase (product_name, price, e_category, date, user_id) " +
            "VALUES (:productName, :price, :eCategory::e_category, :date, :userId)", nativeQuery = true)
    fun saveNewPurchase(productName: String, price: Double, eCategory: ECategory, date: LocalDateTime, userId: Long?)


    @Query(value = "select * from gas_station_purchase WHERE gas_station_purchase.user_id = :userId " +
            "LIMIT :limit OFFSET :offset", nativeQuery = true)
    fun getByUserPageable(userId: Long?, limit: Int, offset: Int): List<GasStationPurchase>

}