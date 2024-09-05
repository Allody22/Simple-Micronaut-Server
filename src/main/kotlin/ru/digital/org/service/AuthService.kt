package ru.digital.org.service

import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import ru.digital.org.model.User
import ru.digital.org.repository.UserRepository

@Singleton
open class AuthService(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) {

    // Подключаем логгер
    private val log: Logger = LoggerFactory.getLogger(AuthService::class.java)

    @Transactional
    open fun registerUser(username: String?, rawPassword: String?): User {
        if (userRepository.existsByUsername(username)) {
            //TODO бросаем экспешен
            return userRepository.findByUsername(username).get()
        }
        val hashedPassword = passwordEncoder.encode(rawPassword)

        val user = User(null, username, hashedPassword)
        log.info("new user $user")
        return userRepository.save(user)
    }

    @Transactional
    open fun loginUser(username: String, password: String): Boolean {
        val userOpt = userRepository.findByUsername(username)
        log.info("user trying to log in $userOpt")
        if (userOpt.isPresent) {
            val user = userOpt.get()
            if (passwordEncoder.matches(password, user.password)) {
                return true
            }
        }

        // Если аутентификация не удалась, возвращаем null
        return false
    }
}