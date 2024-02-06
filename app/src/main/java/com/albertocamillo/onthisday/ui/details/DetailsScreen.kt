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
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.albertocamillo.onthisday.ui.theme.components.NoNetwork

@Composable
fun DetailsScreen() {
    val viewModel = hiltViewModel<DetailsViewModel>()
    val uiState = viewModel.uiState

    if (uiState.offline) {
        NoNetwork()
    } else {

        Column {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                items(uiState.pages) { page ->
                    PageItem(page.displayTitle, onPageClick = {})
                }
            }
        }
    }
}

@Composable
fun PageItem(htmlTitle: String, onPageClick: (String) -> Unit) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .clickable { onPageClick(htmlTitle) },
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = HtmlCompat.fromHtml(htmlTitle, HtmlCompat.FROM_HTML_MODE_COMPACT)
                    .toString(),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}