package com.albertocamillo.onthisday.domain

/**
 * Enum class representing the supported languages for fetching historical events.
 *
 * Each language is associated with a two-letter locale code, used by the Wikipedia OnThisDay API.
 *
 * @property locale The ISO 639-1 code for the language.
 */
enum class Language(val locale: String) {
    ENGLISH("en"),
    GERMAN("de"),
    FRENCH("fr"),
    SWEDISH("sv"),
    PORTUGUESE("pt"),
    RUSSIAN("ru"),
    SPANISH("es"),
    ARABIC("ar"),
    BOSNIAN("bs")
}

/**
 * Resolves the input locale string to a supported language's locale code.
 *
 * If the provided locale is not supported, the function falls back to English ("en").
 *
 * @param name The locale string to match (e.g., "en", "fr").
 * @return A valid locale string supported by the application.
 */
fun getLanguage(name: String): String {
    return Language.entries.firstOrNull {
        it.locale == name
    }?.locale ?: Language.ENGLISH.locale
}
