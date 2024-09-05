package ru.digital.org.controller

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import ru.digital.org.payload.request.LoginRequest
import ru.digital.org.payload.request.RegistrationRequest
import ru.digital.org.payload.response.PingResponse
import ru.digital.org.service.AuthService

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/auth")
class AuthController(
    private val authService: AuthService,
) {

    @Post("/ping")
    @Produces(MediaType.APPLICATION_JSON)
    fun ping(): PingResponse {
        return PingResponse("Hello World!")
    }

    @Post("/register")
    @Produces(MediaType.APPLICATION_JSON)
    fun registerUser(@Body registrationRequest: RegistrationRequest): String {
        return authService.registerUser(registrationRequest.name, registrationRequest.password)
    }

    @Post("/login")
    @Produces(MediaType.APPLICATION_JSON)
    fun loginUser(@Body loginRequest: LoginRequest): String {
        return if (authService.loginUser(loginRequest.name, loginRequest.password)) {
            "success"
        } else {
            "bad request"
        }
    }

}
