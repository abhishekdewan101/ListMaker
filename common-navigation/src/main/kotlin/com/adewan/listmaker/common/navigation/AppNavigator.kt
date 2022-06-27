package com.adewan.listmaker.common.navigation

import androidx.navigation.NavController
import java.util.UUID

interface AppNavigator {
    fun popCurrentRoute()
    fun navigateToAddListScreen()
    fun navigateToListDetailScreen(id: UUID)
    fun navigateToAddGameScreen()
}


class AppNavigatorImpl(private val navController: NavController) : AppNavigator {
    override fun popCurrentRoute() {
        navController.popBackStack()
    }

    override fun navigateToAddListScreen() {
        navController.navigate(Screen.AddList.route)
    }

    override fun navigateToListDetailScreen(id: UUID) {
        val injectedRoute = Screen.ListDetail.route.replace("{listId}", id.toString())
        navController.navigate(injectedRoute)
    }

    override fun navigateToAddGameScreen() {
        navController.navigate(Screen.AddGame.route)
    }
}