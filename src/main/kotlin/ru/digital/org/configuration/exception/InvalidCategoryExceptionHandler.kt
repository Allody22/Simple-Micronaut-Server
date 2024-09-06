package ru.digital.org.configuration.exception

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.server.exceptions.ExceptionHandler
import jakarta.inject.Singleton
import ru.digital.org.model.exception.InvalidCategoryException

@Singleton
class InvalidCategoryExceptionHandler : ExceptionHandler<InvalidCategoryException, HttpResponse<*>> {

    override fun handle(request: HttpRequest<*>, exception: InvalidCategoryException): HttpResponse<*> {
        return HttpResponse
            .status<Any>(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(mapOf("error" to exception.message))
    }
}
