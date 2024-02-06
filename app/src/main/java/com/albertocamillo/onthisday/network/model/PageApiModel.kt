package com.albertocamillo.onthisday.network.model

import com.albertocamillo.onthisday.database.PageEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PageApiModel(
    @Json(name = "pageid") val id: String,
    @Json(name = "normalizedtitle") val normalizedTitle: String,
    @Json(name = "displaytitle") val displayTitle: String,
    @Json(name = "content_urls") val contentUrl: ContentUrlApiModel,
    @Json(name = "thumbnail") val thumbnailSourceUrl: ThumbnailSourceUrlApiModel,
)

fun List<PageApiModel>.asDatabaseModel(selectedEventId: String): List<PageEntity> {
    return map {
        PageEntity(
            selectedEventId = selectedEventId,
            id = it.id,
            displayTitle = it.displayTitle,
            normalizedTitle = it.normalizedTitle,
            thumbnailSource = it.thumbnailSourceUrl.source,
            desktopPageUrl = it.contentUrl.desktop.pageUrl
        )
    }
}