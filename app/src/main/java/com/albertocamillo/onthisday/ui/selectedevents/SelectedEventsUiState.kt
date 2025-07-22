package com.albertocamillo.onthisday.ui.selectedevents

import com.albertocamillo.onthisday.domain.SelectedEvent

/**
 * UI state holder for the Selected Events screen.
 *
 * This data class represents the UI state as exposed by the ViewModel.
 * It contains:
 * - [list]: the list of historical events selected for display
 * - [offline]: a flag indicating whether data could not be fetched and the UI should fall back to an offline state
 *
 * This model is designed for use with Jetpack Compose state management, allowing the UI to reactively
 * update when its values change.
 */
data class SelectedEventsUiState(
    val list: List<SelectedEvent> = listOf(),
    val offline: Boolean = false
)
