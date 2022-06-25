package com.adewan.listmaker.common.navigation

import androidx.navigation.NavController

interface AppNavigator {
    fun popCurrentRoute()
    fun navigateToAddListScreen()
}


class AppNavigatorImpl(private val navController: NavController) : AppNavigator {
    override fun popCurrentRoute() {
        navController.popBackStack()
    }

    override fun navigateToAddListScreen() {
        navController.navigate(Screen.AddList.route)
    }
}