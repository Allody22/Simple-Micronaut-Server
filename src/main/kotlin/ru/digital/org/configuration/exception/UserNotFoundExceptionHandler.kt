package ru.digital.org.configuration.exception

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.server.exceptions.ExceptionHandler
import jakarta.inject.Singleton
import ru.digital.org.model.exception.UserNotFoundException

@Singleton
class UserNotFoundExceptionHandler : ExceptionHandler<UserNotFoundException, HttpResponse<*>> {

    override fun handle(request: HttpRequest<*>, exception: UserNotFoundException): HttpResponse<*> {
        return HttpResponse
            .status<Any>(HttpStatus.NOT_FOUND)
            .body(mapOf("message" to exception.message))
    }
}
