package com.albertocamillo.onthisday.network.model

import com.albertocamillo.onthisday.database.PageEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Data model representing a Wikipedia page related to a historical event,
 * as returned by the Wikimedia "On This Day" API.
 *
 * This class is used to parse the "pages" array in the API response using Moshi.
 *
 * @property id Unique identifier for the Wikipedia page.
 * @property normalizedTitle The simplified page title without formatting.
 * @property displayTitle The formatted page title suitable for display.
 * @property contentUrl Contains platform-specific page URLs (e.g. desktop).
 * @property thumbnailSourceUrl Optional thumbnail image for the page, if available.
 */
@JsonClass(generateAdapter = true)
data class PageApiModel(
    @param:Json(name = "pageid") val id: String,
    @param:Json(name = "normalizedtitle") val normalizedTitle: String,
    @param:Json(name = "displaytitle") val displayTitle: String,
    @param:Json(name = "content_urls") val contentUrl: ContentUrlApiModel,
    @param:Json(name = "thumbnail") val thumbnailSourceUrl: ThumbnailSourceUrlApiModel?,
)

/**
 * Extension function to convert a list of [PageApiModel] into a list of [PageEntity]
 * for insertion into the Room database.
 *
 * @param selectedEventId The ID of the event this page is associated with.
 * @return A list of [PageEntity] objects suitable for local storage.
 */
fun List<PageApiModel>.asDatabaseModel(selectedEventId: String): List<PageEntity> {
    return map {
        PageEntity(
            selectedEventId = selectedEventId,
            id = it.id,
            displayTitle = it.displayTitle,
            normalizedTitle = it.normalizedTitle,
            thumbnailSource = it.thumbnailSourceUrl?.source,
            desktopPageUrl = it.contentUrl.desktop.pageUrl
        )
    }
}
