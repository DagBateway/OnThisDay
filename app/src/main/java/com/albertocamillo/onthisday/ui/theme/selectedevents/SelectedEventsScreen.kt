package com.albertocamillo.onthisday.ui.theme.selectedevents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.albertocamillo.onthisday.domain.SelectedEvent
import com.albertocamillo.onthisday.ui.theme.components.NoNetwork
import java.time.LocalDate

@Composable
fun SelectedEventsScreen(
    onUserClick: (String) -> Unit
) {
    val viewModel = hiltViewModel<SelectedEventsViewModel>()
    val uiState = viewModel.uiState
    val current = LocalDate.now()
    if (uiState.offline) {
        NoNetwork()
    } else {
        Column {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = "On this day, ${current.dayOfMonth}/${current.month.value}",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 30.sp
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                items(uiState.list) { item ->
                    SelectedEventItem(item = item)
                }
            }
        }
    }
}

@Composable
fun SelectedEventItem(item: SelectedEvent) {

    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = "${item.year} ${item.text}",
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}