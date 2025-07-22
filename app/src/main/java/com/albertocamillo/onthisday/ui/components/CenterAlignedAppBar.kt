package com.albertocamillo.onthisday.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * A composable top app bar with the title centred horizontally.
 *
 * Displays a back button if [showIcon] is true. Used for both the main screen and details screen.
 *
 * @param title The title to display in the centre of the top app bar.
 * @param showIcon Whether to show a back button (usually true in detail views).
 * @param onBackClick Callback function triggered when the back button is clicked.
 */
@Composable
fun CenterAligned(title: String, showIcon: Boolean = false, onBackClick: (Boolean) -> Unit) {
    CenterAlignedAppBar(title, showIcon, onBackClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterAlignedAppBar(title: String, showIcon: Boolean, onBackClick: (Boolean) -> Unit) {
    TopAppBar(
        title = {
            // Ensures the title is visually centred even when a navigation icon is present
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(title)
            }
        },
        navigationIcon = {
            if (showIcon) {
                IconButton(onClick = { onBackClick(true) }) {
                    Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null)
                }
            }
        },
    )
}
