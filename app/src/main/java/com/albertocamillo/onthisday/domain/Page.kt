package com.albertocamillo.onthisday.domain

data class Page(
    val id: String,
    val displayTitle: String,
    val normalizedTitle: String,
    val thumbnailUrl: String,
    val desktopPageUrl: String
)
