package com.albertocamillo.onthisday.domain

/**
 * Domain model representing a related Wikipedia page for a historical event.
 * This model is used in the UI and application layers, abstracted from the network and database layers.
 *
 * @property id Unique identifier for the Wikipedia page (usually the page ID from the API).
 * @property displayTitle The full, user-friendly title of the page (may include formatting).
 * @property normalizedTitle A simplified version of the title, typically used for sorting or searching.
 * @property thumbnailUrl Optional URL for the page's thumbnail image, if available.
 * @property desktopPageUrl Direct URL to view the page on Wikipedia's desktop website.
 */
data class Page(
    val id: String,
    val displayTitle: String,
    val normalizedTitle: String,
    val thumbnailUrl: String?,
    val desktopPageUrl: String
)
