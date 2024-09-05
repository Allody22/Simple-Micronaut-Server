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