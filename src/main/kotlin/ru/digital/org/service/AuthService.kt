package ru.digital.org.service

import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import ru.digital.org.model.User
import ru.digital.org.model.exception.UsernameAlreadyExistsException
import ru.digital.org.repository.UserRepository

@Singleton
open class AuthService(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) {

    private val log: Logger = LoggerFactory.getLogger(AuthService::class.java)

    /**
     * Просто регистрируем юзера и сохраняем в бд
     */
    @Transactional
    open fun registerUser(username: String, rawPassword: String): String {
        if (userRepository.existsByUsername(username)) {
            throw UsernameAlreadyExistsException(username)
        }
        val hashedPassword = passwordEncoder.encode(rawPassword)

        val user = User(null, username, hashedPassword)
        userRepository.save(user)
        return "User $username registered successfully."
    }

    /**
     * Проверяем правильные ли данные передал юзер для логина.
     * Если что-то не так, то выкидываем экспешен без объяснений,
     * чтобы никакой хакер не смог подобрать данные, то есть не сообщаем о том, что пользователь не зарегистрирован или тп.
     */
    @Transactional
    open fun loginUser(username: String, password: String): Boolean {
        val userOpt = userRepository.findByUsername(username)
        log.info("user trying to login: $userOpt")
        if (userOpt.isPresent) {
            val user = userOpt.get()
            if (passwordEncoder.matches(password, user.password)) {
                return true
            }
        }

        return false
    }
}