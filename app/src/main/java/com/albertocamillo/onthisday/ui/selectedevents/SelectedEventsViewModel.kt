package com.albertocamillo.onthisday.ui.selectedevents

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertocamillo.onthisday.repository.SelectedEventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * ViewModel for managing and exposing the UI state of the Selected Events screen.
 *
 * This ViewModel:
 * - Uses dependency injection to obtain a reference to the [SelectedEventRepository]
 * - Triggers a refresh of selected events when initialised
 * - Collects the list of selected events from the repository as a Flow
 * - Updates the UI state accordingly, setting an `offline` flag if the list is empty
 *
 * It leverages Kotlin coroutines and state management compatible with Jetpack Compose.
 */

@HiltViewModel
class SelectedEventsViewModel @Inject constructor(
    private val selectedEventRepository: SelectedEventRepository
) : ViewModel() {

    // The state of the UI, initially empty. It will be updated as data is received.
    var uiState by mutableStateOf(SelectedEventsUiState())
        private set

    init {
        // Launch a coroutine to refresh and observe selected events
        viewModelScope.launch(Dispatchers.IO) {
            // Force refresh events from remote or cache
            selectedEventRepository.refreshSelectedEvents()

            // Collect latest list of events from the repository
            selectedEventRepository.selectedEvents.collect { list ->
                // Ensure UI updates happen on the Main thread
                withContext(Dispatchers.Main) {
                    uiState = if (list.isNullOrEmpty()) {
                        uiState.copy(offline = true)
                    } else {
                        uiState.copy(
                            list = list,
                            offline = false
                        )
                    }
                }
            }
        }
    }
}
