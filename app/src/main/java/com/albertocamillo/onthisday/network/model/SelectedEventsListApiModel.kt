package com.albertocamillo.onthisday.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Data model representing the list of selected historical events returned by the API.
 *
 * This model is used to deserialize the root response from the Wikimedia "On This Day" API,
 * where the list of events is provided under the "selected" key.
 *
 * @property selectedEvents The list of individual selected events for a given day,
 *                          each containing year, description, and related pages.
 */
@JsonClass(generateAdapter = true)
data class SelectedEventsListApiModel(
    @param:Json(name = "selected") val selectedEvents: List<SelectedEventApiModel>
)
