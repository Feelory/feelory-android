package com.feelory.feelory

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.feelory.feelory.feature.feed.navigation.feedNavGraph
import com.feelory.feelory.feature.home.navigation.HOME_ROUTE
import com.feelory.feelory.feature.home.navigation.homeNavGraph
import com.feelory.feelory.feature.log.navigation.logNavGraph
import com.feelory.feelory.feature.mypage.navigation.myPageNavGraph

@Composable
fun FeeloryNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = HOME_ROUTE,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        homeNavGraph()
        feedNavGraph()
        logNavGraph()
        myPageNavGraph()
    }
}
