package com.albertocamillo.onthisday.ui.theme.selectedevents

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

@HiltViewModel
class SelectedEventsViewModel @Inject constructor(
    private val selectedEventRepository: SelectedEventRepository
) : ViewModel() {

    var uiState by mutableStateOf(SelectedEventsUiState())
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            selectedEventRepository.refreshSelectedEvents()
            selectedEventRepository.selectedEvents.collect { list ->
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