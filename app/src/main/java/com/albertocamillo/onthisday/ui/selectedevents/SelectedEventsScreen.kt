package com.albertocamillo.onthisday.ui.selectedevents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.albertocamillo.onthisday.domain.SelectedEvent
import com.albertocamillo.onthisday.ui.selectedevents.SelectedEventsViewModel
import com.albertocamillo.onthisday.ui.theme.components.NoNetwork
import java.time.LocalDate

@Composable
fun SelectedEventsScreen(
    onSelectedEventClick: (String) -> Unit
) {
    val viewModel = hiltViewModel<SelectedEventsViewModel>()
    val uiState = viewModel.uiState
    val current = LocalDate.now()
    if (uiState.offline) {
        NoNetwork()
    } else {
        Column {
            CardTitle("On this day, ${current.dayOfMonth}/${current.month.value}")
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                items(uiState.list) { item ->
                    SelectedEventItem(
                        selectedEvent = item,
                        onSelectedEventClick = onSelectedEventClick
                    )
                }
            }
        }
    }
}

@Composable
fun SelectedEventItem(selectedEvent: SelectedEvent, onSelectedEventClick: (String) -> Unit) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .clickable { onSelectedEventClick(selectedEvent.id) },
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = selectedEvent.year.toString(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 30.sp,
            )
            Text(
                text = selectedEvent.text,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
fun CardTitle(title: String) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 30.sp,
        )
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
fun CardTitlePreview() {
    CardTitle("On this day, 1/2")
}