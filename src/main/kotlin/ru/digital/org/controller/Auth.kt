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
import ru.digital.org.service.AuthService

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/auth")
class AuthController(
    private val authService: AuthService,
) {

    @Post("/register")
    @Produces(MediaType.APPLICATION_JSON)
    fun registerUser(@Body registrationRequest: RegistrationRequest): String {
        authService.registerUser(registrationRequest.name, registrationRequest.password)
        return "success"
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
