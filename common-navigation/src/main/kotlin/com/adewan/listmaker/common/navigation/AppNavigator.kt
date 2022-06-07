package com.adewan.listmaker.common.navigation

import androidx.navigation.NavController

interface AppNavigator {
    fun navigateToSettings()
    fun popCurrentRoute()
}


class AppNavigatorImpl(private val navController: NavController) : AppNavigator {
    override fun navigateToSettings() {
        navController.navigate(Screen.Settings.route)
    }

    override fun popCurrentRoute() {
        navController.popBackStack()
    }
}