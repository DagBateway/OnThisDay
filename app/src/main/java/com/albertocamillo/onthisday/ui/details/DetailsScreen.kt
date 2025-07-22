package com.albertocamillo.onthisday.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.albertocamillo.onthisday.R
import com.albertocamillo.onthisday.ui.components.CenterAligned
import com.albertocamillo.onthisday.ui.components.NoNetwork

/**
 * Composable for displaying a list of related Wikipedia pages for a selected historical event.
 *
 * This screen is accessed after the user selects an event on the main screen.
 * It shows a list of pages (with optional thumbnails), and tapping an item opens the corresponding Wikipedia link.
 *
 * @param onPageClick Callback when a user clicks a page card (passes URL to open).
 * @param onBackClick Callback for handling back navigation.
 */
@Composable
fun DetailsScreen(onPageClick: (String) -> Unit, onBackClick: (Boolean) -> Unit) {
    val viewModel = hiltViewModel<DetailsViewModel>()
    val uiState = viewModel.uiState

    if (uiState.offline) {
        // Show offline message or animation
        NoNetwork()
    } else {
        Column {
            // Top bar with title and back button
            CenterAligned(stringResource(id = R.string.related_pages), true, onBackClick)

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                // Display each Wikipedia page in a card
                items(uiState.pages) { page ->
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize()
                            .clickable { onPageClick(page.desktopPageUrl) },
                    ) {
                        Column {
                            // Show thumbnail image if available
                            page.thumbnailUrl?.let {
                                AsyncImage(
                                    placeholder = painterResource(R.drawable.image_missing),
                                    modifier = Modifier.fillMaxSize(),
                                    model = it,
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                            }
                            // Page title
                            Text(
                                modifier = Modifier.padding(16.dp),
                                text = page.normalizedTitle,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                }
            }
        }
    }
}
