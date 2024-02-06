package com.albertocamillo.onthisday.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContentUrlApiModel(
    @Json(name = "desktop") val desktop: DesktopContentUrlApiModel
)