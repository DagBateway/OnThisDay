package com.albertocamillo.onthisday.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.albertocamillo.onthisday.domain.SelectedEvent
import com.albertocamillo.onthisday.utils.generateUniqueID

/**
 * Room database entity representing a selected historical event
 * returned by the Wikimedia "On This Day" API.
 *
 * Each entry corresponds to a single event for a given date and language.
 *
 * @property id Unique identifier generated from the event's year and text.
 * @property text Description of the historical event.
 * @property year The year in which the event occurred.
 * @property month The month associated with the selected date.
 * @property day The day associated with the selected date.
 */
@Entity
data class SelectedEventEntity(
    @PrimaryKey
    val id: String,
    val text: String,
    val year: Int,
    val month: Int,
    val day: Int
)

/**
 * Extension function that maps a list of [SelectedEventEntity] objects
 * to their domain model representation [SelectedEvent].
 *
 * The domain model is used in the UI and business logic layers.
 *
 * @return List of [SelectedEvent] objects.
 */
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
