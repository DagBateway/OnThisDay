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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.albertocamillo.onthisday.R
import com.albertocamillo.onthisday.ui.theme.components.NoNetwork
import com.albertocamillo.onthisday.ui.selectedevents.CardTitle
import java.util.Locale

@Composable
fun DetailsScreen(onPageClick: (String) -> Unit) {
    val viewModel = hiltViewModel<DetailsViewModel>()
    val uiState = viewModel.uiState

    if (uiState.offline) {
        NoNetwork()
    } else {

        Column {
            CardTitle("Related Pages")
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                items(uiState.pages) { page ->
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp
                        ),
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize()
                            .clickable { onPageClick(page.desktopPageUrl) },
                    ) {
                        Column {
                            page.thumbnailUrl?.let {
                                AsyncImage(
                                    placeholder = painterResource(R.drawable.image_missing),
                                    modifier = Modifier.fillMaxSize(),
                                    model = it,
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                            }
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
