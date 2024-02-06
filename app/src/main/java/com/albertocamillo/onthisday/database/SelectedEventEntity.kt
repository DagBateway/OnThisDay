package com.albertocamillo.onthisday.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.albertocamillo.onthisday.domain.SelectedEvent
import com.albertocamillo.onthisday.utils.generateUniqueID

@Entity
data class SelectedEventEntity(
    @PrimaryKey
    val id: String,
    val text: String,
    val year: Int,
    val month: Int,
    val day: Int
)

fun List<SelectedEventEntity>.asDomainModel(): List<SelectedEvent> {
    return map {
        SelectedEvent(
            id = generateUniqueID(it.year.toString(), it.text),
            text = it.text,
            year = it.year,
            month = it.month,
            day = it.day
        )
    }
}
