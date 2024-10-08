
package ru.digital.org.repository

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import ru.digital.org.model.User
import java.util.*

@JdbcRepository(dialect = Dialect.POSTGRES)
interface UserRepository : CrudRepository<User, Long> {

    fun existsByUsername(username: String?): Boolean

    fun findByUsername(username: String?): Optional<User>
}