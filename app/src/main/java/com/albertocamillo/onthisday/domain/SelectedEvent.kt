package com.albertocamillo.onthisday.domain

/**
 * Domain model representing a historical event that occurred on a specific day.
 * This model is used across the UI layer and application logic, decoupled from
 * database and network representations.
 *
 * @property id Unique identifier for the event, typically generated using a hash of its contents.
 * @property text A short description of the event.
 * @property year The year the event took place.
 * @property month The month the event occurred.
 * @property day The day of the month the event occurred.
 */
data class SelectedEvent(
    val id: String,
    val text: String,
    val year: Int,
    val month: Int,
    val day: Int,
)
