package ru.digital.org.configuration.security


import jakarta.inject.Singleton
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Singleton
class BCryptPasswordEncoderService: PasswordEncoder {

    private val delegate: PasswordEncoder = BCryptPasswordEncoder()

    override fun encode(rawPassword: CharSequence?): String {
        return delegate.encode(rawPassword)
    }

    override fun matches(rawPassword: CharSequence?, encodedPassword: String?): Boolean {
        return delegate.matches(rawPassword, encodedPassword)
    }


}