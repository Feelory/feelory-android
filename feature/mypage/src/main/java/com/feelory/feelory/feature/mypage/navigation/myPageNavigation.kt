package com.feelory.feelory.feature.mypage.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.feelory.feelory.feature.mypage.MyPageRoute

const val MY_PAGE_ROUTE = "myPageRoute"

fun NavController.navigateMyPage(navOptions: NavOptions? = null) {
    navigate(MY_PAGE_ROUTE, navOptions)
}

fun NavGraphBuilder.myPageNavGraph() {
    composable(
        route = MY_PAGE_ROUTE,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        MyPageRoute()
    }
}
