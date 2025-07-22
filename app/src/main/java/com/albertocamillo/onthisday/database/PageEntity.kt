package com.albertocamillo.onthisday.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.albertocamillo.onthisday.domain.Page

/**
 * Room database entity representing a Wikipedia page related to a historical event.
 *
 * Each [PageEntity] entry is linked to a [SelectedEventEntity] via the [selectedEventId] foreign key.
 * This allows the app to display additional context about events using related articles.
 *
 * @property id Unique page ID as provided by the API (used as primary key).
 * @property displayTitle Title used for UI display.
 * @property normalizedTitle Normalised title used internally or for comparisons.
 * @property selectedEventId ID of the [SelectedEventEntity] this page is related to.
 * @property thumbnailSource Optional URL to a thumbnail image (may be null).
 * @property desktopPageUrl Full URL to the desktop version of the Wikipedia page.
 */
@Entity
data class PageEntity(
    @PrimaryKey
    val id: String,
    val displayTitle: String,
    val normalizedTitle: String,
    val selectedEventId: String,
    val thumbnailSource: String?,
    val desktopPageUrl: String
)

/**
 * Maps a list of [PageEntity] instances from the database to the domain-level [Page] model.
 *
 * This conversion ensures that UI components and business logic can rely on clean,
 * API-agnostic models without database annotations.
 *
 * @return A list of [Page] domain models.
 */
fun List<PageEntity>.asDomainModel(): List<Page> {
    return map {
        Page(
            id = it.id,
            normalizedTitle = it.normalizedTitle,
            displayTitle = it.displayTitle,
            thumbnailUrl = it.thumbnailSource,
            desktopPageUrl = it.desktopPageUrl
        )
    }
}
