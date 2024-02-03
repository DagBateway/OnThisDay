package com.albertocamillo.onthisday.ui.theme.selectedevents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.albertocamillo.onthisday.domain.SelectedEvent
import com.albertocamillo.onthisday.ui.theme.components.NoNetwork

@Composable
fun SelectedEventsScreen(
    onUserClick: (String) -> Unit
) {
    val viewModel = hiltViewModel<SelectedEventsViewModel>()
    val uiState = viewModel.uiState

    if (uiState.offline) {
        NoNetwork()
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            items(uiState.list) { item ->
                SelectedEventItem(item = item, onUserClick = onUserClick)
            }
        }
    }
}

@Composable
fun SelectedEventItem(item: SelectedEvent, onUserClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onUserClick(item.year.toString()) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = "${item.year} ${item.text}",
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}