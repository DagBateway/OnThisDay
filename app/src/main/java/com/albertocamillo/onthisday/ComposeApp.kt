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

@Composable
fun ComposeApp() {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.White,
            darkIcons = true
        )
    }

    val navController = rememberNavController()
    val uriHandler = LocalUriHandler.current

    NavHost(
        navController = navController,
        startDestination = Route.SELECTED_EVENTS
    ) {
        composable(Route.SELECTED_EVENTS) { backStackEntry ->
            SelectedEventsScreen(
                onSelectedEventClick = { selectedEventId ->
                    if (backStackEntry.getLifecycle().currentState == Lifecycle.State.RESUMED) {
                        navController.navigate("${Route.DETAILS}/$selectedEventId")
                    }
                },
                onBackClick = {}
            )
        }
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
                    if (goingBack) {
                        if (backStackEntry.getLifecycle().currentState == Lifecycle.State.RESUMED) {
                            navController.navigateUp()
                        }
                    }
                }
            )
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
