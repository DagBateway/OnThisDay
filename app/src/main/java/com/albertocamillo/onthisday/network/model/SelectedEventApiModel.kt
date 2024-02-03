package com.albertocamillo.onthisday.network.model

import com.albertocamillo.onthisday.database.SelectedEventEntity
import com.albertocamillo.onthisday.utils.generateUniqueID
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SelectedEventApiModel(
    @Json(name = "text") val text: String,
    @Json(name = "year") val year: Int
)

fun List<SelectedEventApiModel>.asDatabaseModel(): List<SelectedEventEntity> {
    return map {
        SelectedEventEntity(
            id = generateUniqueID(it.year.toString(), it.text),
            text = it.text,
            year = it.year
        )
    }
}