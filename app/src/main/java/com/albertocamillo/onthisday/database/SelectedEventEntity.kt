package com.albertocamillo.onthisday.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.albertocamillo.onthisday.domain.SelectedEvent

@Entity
data class SelectedEventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val text: String,
    val year: Int
)

fun List<SelectedEventEntity>.asDomainModel(): List<SelectedEvent> {
    return map {
        SelectedEvent(
            id = it.id,
            text = it.text,
            year = it.year
        )
    }
}
