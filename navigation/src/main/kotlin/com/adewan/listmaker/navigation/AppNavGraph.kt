package com.adewan.listmaker.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.adewan.listmaker.common.navigation.AppNavigatorImpl
import com.adewan.listmaker.common.navigation.Screen
import com.adewan.listmaker.ui.home.HomeScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavGraph() {
    val navController = rememberAnimatedNavController()

    val appNavigator = AppNavigatorImpl(navController = navController)

    AnimatedNavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navigator = appNavigator)
        }
    }
}