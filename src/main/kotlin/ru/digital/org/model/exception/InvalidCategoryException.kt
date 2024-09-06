package ru.digital.org.model.exception

class InvalidCategoryException(message: String) : RuntimeException("No category found for $message")