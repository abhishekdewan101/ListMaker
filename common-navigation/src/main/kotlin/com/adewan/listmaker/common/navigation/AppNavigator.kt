package com.adewan.listmaker.common.navigation

import androidx.navigation.NavController

interface AppNavigator {
    fun popCurrentRoute()
}


class AppNavigatorImpl(private val navController: NavController) : AppNavigator {
    override fun popCurrentRoute() {
        navController.popBackStack()
    }
}