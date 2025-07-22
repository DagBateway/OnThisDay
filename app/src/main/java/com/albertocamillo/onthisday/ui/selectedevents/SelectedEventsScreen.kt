package com.albertocamillo.onthisday.ui.selectedevents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.albertocamillo.onthisday.R
import com.albertocamillo.onthisday.domain.SelectedEvent
import com.albertocamillo.onthisday.ui.components.CenterAligned
import com.albertocamillo.onthisday.ui.components.NoNetwork
import com.albertocamillo.onthisday.utils.LoadingLottieAnimation
import java.time.LocalDate

/**
 * Main screen that displays a list of historical events for the current date.
 *
 * If data is not available (likely due to lack of internet), it shows a fallback offline message.
 * While the data is being loaded, it shows a Lottie animation.
 * Once loaded, it displays each event in a scrollable list.
 *
 * @param onSelectedEventClick Callback when a user taps an event card.
 * @param onBackClick Callback for the back navigation (not used in this screen).
 */
@Composable
fun SelectedEventsScreen(
    onSelectedEventClick: (String) -> Unit,
    onBackClick: (Boolean) -> Unit
) {
    val viewModel = hiltViewModel<SelectedEventsViewModel>()
    val uiState = viewModel.uiState
    val current = LocalDate.now()

    if (uiState.offline) {
        NoNetwork()
    } else {
        Column {
            // Top bar with date and back button
            CenterAligned(
                stringResource(id = R.string.on_this_day) + ", ${current.dayOfMonth}/${current.month.value}",
                onBackClick = onBackClick
            )
            // Shows loader while data is being fetched
            LoadingAnimation(uiState.list.isEmpty())

            // List of selected events
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

/**
 * Displays a centered loading animation when data is being fetched.
 */
@Composable
fun LoadingAnimation(isVisible: Boolean) {
    if (isVisible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            LoadingLottieAnimation(modifier = Modifier.align(alignment = Alignment.Center))
        }
    }
}

/**
 * A single card item displaying an event's year and description.
 *
 * @param selectedEvent Event to show.
 * @param onSelectedEventClick Triggered when the card is clicked.
 */
@Composable
fun SelectedEventItem(selectedEvent: SelectedEvent, onSelectedEventClick: (String) -> Unit) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
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
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .wrapContentWidth(align = Alignment.CenterHorizontally),
                text = stringResource(R.string.read_more),
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

/**
 * Small helper composable for previewing card titles (not used in production).
 */
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
