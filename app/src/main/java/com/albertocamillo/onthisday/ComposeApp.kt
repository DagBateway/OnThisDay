package com.albertocamillo.onthisday

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.albertocamillo.onthisday.ui.details.DetailsScreen
import com.albertocamillo.onthisday.ui.selectedevents.SelectedEventsScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Root Composable for the OnThisDay app.
 *
 * Responsibilities:
 * - Sets the system UI (status/navigation bar) to a white background with dark icons.
 * - Defines and manages the app's navigation graph using Jetpack Compose Navigation.
 * - Hosts the two main screens:
 *   - [SelectedEventsScreen]: the main list of notable historical events.
 *   - [DetailsScreen]: shows detailed info about a specific selected event.
 *
 * Also manages back stack state and deep linking via NavArguments.
 */
@Composable
fun ComposeApp() {
    // Set system bars to white with dark icons for visibility
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.White,
            darkIcons = true
        )
    }

    val navController = rememberNavController()
    val uriHandler = LocalUriHandler.current

    // Define navigation graph
    NavHost(
        navController = navController,
        startDestination = Route.SELECTED_EVENTS
    ) {
        // Main screen: list of selected events
        composable(Route.SELECTED_EVENTS) { backStackEntry ->
            SelectedEventsScreen(
                onSelectedEventClick = { selectedEventId ->
                    // Prevent duplicate navigation if screen is not in resumed state
                    if (backStackEntry.getLifecycle().currentState == Lifecycle.State.RESUMED) {
                        navController.navigate("${Route.DETAILS}/$selectedEventId")
                    }
                },
                onBackClick = {} // Currently unused
            )
        }

        // Details screen: event details based on selected ID
        composable(
            route = "${Route.DETAILS}/{${Argument.SELECTED_EVENT_ID}}",
            arguments = listOf(
                navArgument(Argument.SELECTED_EVENT_ID) {
                    type = NavType.StringType
                }
            ),
        ) { backStackEntry ->
            DetailsScreen(
                onPageClick = { pageUrl -> uriHandler.openUri(pageUrl) },
                onBackClick = { goingBack ->
                    if (goingBack && backStackEntry.getLifecycle().currentState == Lifecycle.State.RESUMED) {
                        navController.navigateUp()
                    }
                }
            )
        }
    }
}

// Defines the route names used in navigation
object Route {
    const val SELECTED_EVENTS = "selected_events"
    const val DETAILS = "details"
}

// Defines the argument keys passed through navigation routes
object Argument {
    const val SELECTED_EVENT_ID = "selectedEventId"
}
