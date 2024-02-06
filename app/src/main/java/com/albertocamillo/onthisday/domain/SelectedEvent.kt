package com.albertocamillo.onthisday.domain

data class SelectedEvent(
    val id: String,
    val text: String,
    val year: Int,
    val month: Int,
    val day: Int,
)