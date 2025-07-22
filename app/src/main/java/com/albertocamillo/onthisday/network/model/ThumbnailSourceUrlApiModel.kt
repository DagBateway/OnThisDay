package com.albertocamillo.onthisday.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Data model representing the thumbnail image URL of a related Wikipedia page,
 * as returned by the API response.
 *
 * This class is used for parsing JSON data using Moshi.
 *
 * @property source The direct URL to the image thumbnail.
 */
@JsonClass(generateAdapter = true)
data class ThumbnailSourceUrlApiModel(
    @param:Json(name = "source") val source: String
)
