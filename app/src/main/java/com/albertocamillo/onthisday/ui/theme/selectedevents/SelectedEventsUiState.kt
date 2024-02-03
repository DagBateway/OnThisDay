package com.albertocamillo.onthisday.ui.theme.selectedevents

import com.albertocamillo.onthisday.domain.SelectedEvent

data class SelectedEventsUiState(
    val list: List<SelectedEvent> = listOf(),
    val offline: Boolean = false
)
