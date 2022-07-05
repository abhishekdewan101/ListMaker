package com.adewan.listmaker.common.navigation

import androidx.navigation.NavController
import com.adewan.listmaker.models.CollectionType
import java.util.UUID

interface AppNavigator {
    fun popCurrentRoute()
    fun navigateToAddListScreen()
    fun navigateToListDetailScreen(id: UUID, type: CollectionType)
    fun navigateToAddGameScreen(id: String)
}


class AppNavigatorImpl(private val navController: NavController) : AppNavigator {
    override fun popCurrentRoute() {
        navController.popBackStack()
    }

    override fun navigateToAddListScreen() {
        navController.navigate(Screen.AddList.route)
    }

    override fun navigateToListDetailScreen(
        id: UUID,
        type: CollectionType
    ) {
        when (type) {
            CollectionType.GAMES -> {
                val injectedRoute = Screen.GameListDetail.route.replace("{listId}", id.toString())
                navController.navigate(injectedRoute)
            }
            CollectionType.MOVIES -> {
                val injectedRoute = Screen.MovieListDetail.route.replace("{listId}", id.toString())
                navController.navigate(injectedRoute)
            }
            else -> {}
        }

    }

    override fun navigateToAddGameScreen(id: String) {
        val injectedRoute = Screen.AddGame.route.replace("{parentId}", id.toString())
        navController.navigate(injectedRoute)
    }
}