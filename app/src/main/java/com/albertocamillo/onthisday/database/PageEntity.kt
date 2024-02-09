package com.albertocamillo.onthisday.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.albertocamillo.onthisday.domain.Page

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
