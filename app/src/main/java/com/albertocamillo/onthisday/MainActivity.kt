package com.albertocamillo.onthisday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.albertocamillo.onthisday.ui.theme.OnThisDayTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main entry point for the OnThisDay app.
 *
 * Responsibilities:
 * - Hosts the root Compose content.
 * - Applies the custom app theme using [OnThisDayTheme].
 * - Launches the [ComposeApp] composable which contains the app's navigation graph and screens.
 *
 * Annotated with [@AndroidEntryPoint] to enable dependency injection using Hilt in this activity.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnThisDayTheme {
                ComposeApp() // Starts the app's composable navigation structure
            }
        }
    }
}
