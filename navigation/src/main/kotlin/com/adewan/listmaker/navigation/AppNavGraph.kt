package com.adewan.listmaker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adewan.listmaker.common.navigation.AppNavigatorImpl
import com.adewan.listmaker.common.navigation.Screen
import com.adewan.listmaker.ui.home.HomeScreen
import com.adewan.ui.settings.SettingsScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    val appNavigator = AppNavigatorImpl(navController = navController)

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navigator = appNavigator)
        }
        composable(route = Screen.Settings.route) {
            SettingsScreen(navigator = appNavigator)
        }
    }

}