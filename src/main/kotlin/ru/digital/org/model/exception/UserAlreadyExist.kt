package ru.digital.org.model.exception


class UsernameAlreadyExistsException(username: String?) : RuntimeException("Username $username already exists")