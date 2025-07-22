package com.albertocamillo.onthisday.ui.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertocamillo.onthisday.Argument
import com.albertocamillo.onthisday.repository.PagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * ViewModel for the Details screen.
 *
 * It retrieves and observes a list of Wikipedia pages related to a selected historical event,
 * using the event ID passed via navigation arguments.
 *
 * If no data is found, it marks the UI as offline.
 *
 * @param pagesRepository Repository for fetching Wikipedia pages.
 * @param savedStateHandle Used to retrieve the event ID from navigation arguments.
 */
@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val pagesRepository: PagesRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    // Retrieves the selected event ID from the navigation argument
    private val selectedEventId: String? = savedStateHandle[Argument.SELECTED_EVENT_ID]

    // Holds the UI state, including pages and offline status
    var uiState by mutableStateOf(DetailsUiState())
        private set

    init {
        // If we have a valid event ID, fetch related pages from the repository
        selectedEventId?.let {
            viewModelScope.launch(Dispatchers.IO) {
                pagesRepository.getPages(it).collect { pages ->
                    withContext(Dispatchers.Main) {
                        uiState = if (pages == null) {
                            uiState.copy(offline = true)
                        } else {
                            uiState.copy(
                                pages = pages,
                                offline = false
                            )
                        }
                    }
                }
            }
        }
    }
}
