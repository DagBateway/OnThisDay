package com.albertocamillo.onthisday

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalUriHandler
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.albertocamillo.onthisday.ui.details.DetailsScreen
import com.albertocamillo.onthisday.ui.theme.selectedevents.SelectedEventsScreen

@Composable
fun ComposeApp() {
    val navController = rememberNavController()
    val uriHandler = LocalUriHandler.current

    NavHost(
        navController = navController,
        startDestination = Route.SELECTED_EVENTS
    ) {
        composable(Route.SELECTED_EVENTS) { backStackEntry ->
            SelectedEventsScreen(
                onSelectedEventClick = { selectedEventId ->
                    // In order to discard duplicated navigation events, we check the Lifecycle
                    if (backStackEntry.getLifecycle().currentState == Lifecycle.State.RESUMED) {
                        navController.navigate("${Route.DETAILS}/$selectedEventId")
                    }
                })
        }
        composable(
            route = "${Route.DETAILS}/{${Argument.SELECTED_EVENT_ID}}",
            arguments = listOf(
                navArgument(Argument.SELECTED_EVENT_ID) {
                    type = NavType.StringType
                }
            ),
        ) {
            DetailsScreen(onPageClick = { pageUrl ->
                uriHandler.openUri(pageUrl)
            })
        }
    }
}

object Route {
    const val SELECTED_EVENTS = "selected_events"
    const val DETAILS = "details"
}

object Argument {
    const val SELECTED_EVENT_ID = "selectedEventId"
}