package ru.digital.org.model.constants

import java.util.*

enum class ECategory {
    GASOLINE, CAFE, SERVICES, UNKNOWN_CATEGORY;

    companion object {
        fun fromString(category: String): ECategory {
            return try {
                enumValueOf<ECategory>(category.uppercase(Locale.getDefault()))
            } catch (e: IllegalArgumentException) {
                UNKNOWN_CATEGORY
            }
        }
    }
}