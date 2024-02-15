package com.albertocamillo.onthisday.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CenterAligned(title: String, showIcon: Boolean = false, onBackClick: (Boolean) -> Unit) {
    CenterAlignedAppBar(title, showIcon, onBackClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterAlignedAppBar(title: String, showIcon: Boolean, onBackClick: (Boolean) -> Unit) {
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(title)
            }
        },
        navigationIcon = {
            if (showIcon) {
                IconButton(onClick = {
                    onBackClick(true)
                }) {
                    Icon(Icons.Rounded.ArrowBack, "")
                }
            }

        },
    )
}