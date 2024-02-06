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

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val pagesRepository: PagesRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val selectedEventId: String? = savedStateHandle[Argument.SELECTED_EVENT_ID]
    var uiState by mutableStateOf(DetailsUiState())
        private set

    init {
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