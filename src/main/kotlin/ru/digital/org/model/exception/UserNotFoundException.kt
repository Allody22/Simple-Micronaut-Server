package ru.digital.org.model.exception


class UserNotFoundException(username: String?) : RuntimeException("User with name $username not found")