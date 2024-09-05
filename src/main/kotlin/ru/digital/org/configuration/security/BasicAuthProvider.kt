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