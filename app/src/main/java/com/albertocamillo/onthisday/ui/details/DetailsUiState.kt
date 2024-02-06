package com.albertocamillo.onthisday.ui.details

import com.albertocamillo.onthisday.domain.Page

data class DetailsUiState(
    val pages: List<Page> = emptyList(),
    val offline: Boolean = false
)