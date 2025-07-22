package com.albertocamillo.onthisday.ui.details

import com.albertocamillo.onthisday.domain.Page

/**
 * UI state holder for the Details screen.
 *
 * @property pages A list of Wikipedia pages related to the selected historical event.
 * @property offline Flag indicating whether the app failed to retrieve data and is in offline mode.
 */
data class DetailsUiState(
    val pages: List<Page> = emptyList(),
    val offline: Boolean = false
)
