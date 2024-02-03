package com.albertocamillo.onthisday.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.albertocamillo.onthisday.domain.Page

@Entity
data class PageEntity(
    @PrimaryKey
    val id: String,
    val displayTitle: String,
)

fun List<PageEntity>.asDomainModel(): List<Page> {
    return map {
        Page(
            id = it.id,
            displayTitle = it.displayTitle
        )
    }
}
