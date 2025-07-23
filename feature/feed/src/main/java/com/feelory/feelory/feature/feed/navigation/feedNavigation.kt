package com.feelory.feelory.feature.feed.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.feelory.feelory.feature.feed.FeedRoute

const val FEED_ROUTE = "feedRoute"

fun NavController.navigateFeed(navOptions: NavOptions? = null) {
    navigate(FEED_ROUTE, navOptions)
}

fun NavGraphBuilder.feedNavGraph() {
    composable(
        route = FEED_ROUTE,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        FeedRoute()
    }
}
