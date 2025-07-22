package com.albertocamillo.onthisday.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Data model representing the desktop-specific content URL of a Wikipedia page,
 * as returned in the Wikimedia API response.
 *
 * This is typically nested under "content_urls.desktop.page".
 *
 * @property pageUrl The direct URL to the desktop version of the Wikipedia page.
 */
@JsonClass(generateAdapter = true)
data class DesktopContentUrlApiModel(
    @param:Json(name = "page") val pageUrl: String
)
