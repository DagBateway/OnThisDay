package com.albertocamillo.onthisday.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Data model representing the content URLs (desktop) associated with a Wikipedia page,
 * as provided in the Wikimedia API response.
 *
 * This class wraps around the `desktop` field which contains a URL pointing
 * to the desktop version of the Wikipedia article.
 *
 * @property desktop The object holding the desktop page URL.
 */
@JsonClass(generateAdapter = true)
data class ContentUrlApiModel(
    @param:Json(name = "desktop") val desktop: DesktopContentUrlApiModel
)
