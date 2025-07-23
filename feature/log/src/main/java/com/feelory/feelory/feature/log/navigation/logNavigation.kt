package com.feelory.feelory.feature.log.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.feelory.feelory.feature.log.LogRoute

const val LOG_ROUTE = "logRoute"

fun NavController.navigateLog(navOptions: NavOptions? = null) {
    navigate(LOG_ROUTE, navOptions)
}

fun NavGraphBuilder.logNavGraph() {
    composable(
        route = LOG_ROUTE,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        LogRoute()
    }
}
