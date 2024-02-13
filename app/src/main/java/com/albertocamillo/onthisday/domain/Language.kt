package com.albertocamillo.onthisday.domain

enum class Language(val locale: String) {
    ENGLISH("en"),
    GERMAN("de"),
    FRENCH("fr"),
    SWEDISH("sv"),
    PORTUGUESE("pt"),
    RUSSIAN("ru"),
    SPANISH("es"),
    ARABIC("es"),
    BOSNIAN("bs")
}

fun getLanguage(name: String): String {
    return Language.entries.firstOrNull {
        it.locale == name
    }?.locale ?: Language.ENGLISH.locale
}