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
package ru.digital.org.configuration.security

import io.micronaut.core.annotation.NonNull
import io.micronaut.core.annotation.Nullable
import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import io.micronaut.security.authentication.provider.HttpRequestAuthenticationProvider
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.springframework.security.crypto.password.PasswordEncoder
import ru.digital.org.model.User
import ru.digital.org.repository.UserRepository
import java.util.*


@Singleton
class AppAuthenticationProvider @Inject constructor(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : HttpRequestAuthenticationProvider<Any> {

    override fun authenticate(
        @Nullable request: HttpRequest<Any>?,
        @NonNull authenticationRequest: AuthenticationRequest<String, String>
    ): AuthenticationResponse {
        val username = authenticationRequest.identity
        val password = authenticationRequest.secret

        val userOpt: Optional<User> = userRepository.findByUsername(username)

        if (userOpt.isPresent) {
            val user = userOpt.get()

            // Проверка пароля
            if (passwordEncoder.matches(password, user.password)) {
                return AuthenticationResponse.success(user.username!!)
            }
        }

        return AuthenticationResponse.failure()
    }
}