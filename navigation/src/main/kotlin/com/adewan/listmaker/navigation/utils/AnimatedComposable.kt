package com.adewan.listmaker.navigation.utils

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.enterFromBottomExitToBottom(route: String, content: @Composable () -> Unit) {
    composable(route = route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Up,
                animationSpec = tween(250)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Down,
                animationSpec = tween(250)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Up,
                animationSpec = tween(250)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Down,
                animationSpec = tween(250)
            )
        }) {
        content()
    }
}