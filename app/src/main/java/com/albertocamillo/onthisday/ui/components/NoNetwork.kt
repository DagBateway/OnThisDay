package com.albertocamillo.onthisday.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.albertocamillo.onthisday.R

/**
 * Composable that displays a visual indication that the app is offline.
 *
 * This UI component is used when the app cannot fetch data due to lack of internet connectivity.
 * It shows an offline icon and a message prompting the user to check their network connection.
 */
@Composable
fun NoNetwork() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 16.dp),
                painter = painterResource(R.drawable.ic_offline),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null // decorative icon
            )
            Text(
                text = stringResource(id = R.string.please_check_network_connection),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
