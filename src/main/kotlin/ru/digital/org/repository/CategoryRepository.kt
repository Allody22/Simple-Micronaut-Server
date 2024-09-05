
package ru.digital.org.repository

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import ru.digital.org.model.Category
import ru.digital.org.model.constants.ECategory
import java.util.*

@JdbcRepository(dialect = Dialect.POSTGRES)
interface CategoryRepository : CrudRepository<Category, Long> {

    fun findByName(name: ECategory): Optional<Category>
}