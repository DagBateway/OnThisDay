package com.albertocamillo.onthisday

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.albertocamillo.onthisday.ui.theme.selectedevents.SelectedEventsScreen

@Composable
fun ComposeApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.SELECTED_EVENTS
    ) {
        composable(Route.SELECTED_EVENTS) {
            SelectedEventsScreen(
                onUserClick = {
                }
            )
        }
    }
}

object Route {
    const val SELECTED_EVENTS = "selected_events"
}
