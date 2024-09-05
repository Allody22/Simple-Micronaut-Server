package ru.digital.org.configuration.exception

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.server.exceptions.ExceptionHandler
import jakarta.inject.Singleton
import ru.digital.org.model.exception.UsernameAlreadyExistsException

@Singleton
class UsernameAlreadyExistsExceptionHandler : ExceptionHandler<UsernameAlreadyExistsException, HttpResponse<*>> {

    override fun handle(request: HttpRequest<*>, exception: UsernameAlreadyExistsException): HttpResponse<*> {
        return HttpResponse
            .status<Any>(HttpStatus.CONFLICT) // Указываем статус 409
            .body(mapOf("error" to exception.message)) // Можно добавить тело ответа
    }
}
