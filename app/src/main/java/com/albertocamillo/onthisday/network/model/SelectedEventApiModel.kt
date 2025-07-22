package com.albertocamillo.onthisday.network.model

import com.albertocamillo.onthisday.database.SelectedEventEntity
import com.albertocamillo.onthisday.utils.generateUniqueID
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Data model representing a single historical event returned by the Wikimedia "On This Day" API.
 *
 * Each event includes a description, a year, and a list of related Wikipedia pages.
 *
 * This model is used for parsing JSON responses using Moshi.
 *
 * @property text A short description of the historical event.
 * @property year The year in which the event occurred.
 * @property pages A list of related Wikipedia pages, used for further detail.
 */
@JsonClass(generateAdapter = true)
data class SelectedEventApiModel(
    @param:Json(name = "text") val text: String,
    @param:Json(name = "year") val year: Int,
    @param:Json(name = "pages") val pages: List<PageApiModel>,
)

/**
 * Extension function to convert a list of [SelectedEventApiModel] into a list of
 * [SelectedEventEntity] for insertion into the local Room database.
 *
 * A unique ID is generated for each event using a SHA-256 hash of the year and event text.
 *
 * @param month The month for which the event was fetched (1–12).
 * @param day The day for which the event was fetched (1–31).
 * @return A list of [SelectedEventEntity] objects ready for database storage.
 */
fun List<SelectedEventApiModel>.asDatabaseModel(
    month: Int,
    day: Int
): List<SelectedEventEntity> {
    return map {
        SelectedEventEntity(
            id = generateUniqueID(it.year.toString(), it.text),
            text = it.text,
            year = it.year,
            month = month,
            day = day
        )
    }
}
