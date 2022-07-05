@file:OptIn(ExperimentalAnimationApi::class)

package com.adewan.listmaker.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.adewan.listmaker.add.game.AddGameScreen
import com.adewan.listmaker.common.navigation.AppNavigatorImpl
import com.adewan.listmaker.common.navigation.Screen
import com.adewan.listmaker.game.list.details.GameListDetailScreen
import com.adewan.listmaker.list.create.CreateListScreen
import com.adewan.listmaker.navigation.utils.enterFromBottomExitToBottom
import com.adewan.listmaker.navigation.utils.enterFromRightExitToRight
import com.adewan.listmaker.ui.home.HomeScreen
import com.adewan.listmaker.ui.movie.list.details.MovieListDetailScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@Composable
fun AppNavGraph() {
    val navController = rememberAnimatedNavController()

    val appNavigator = AppNavigatorImpl(navController = navController)

    AnimatedNavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navigator = appNavigator)
        }

        enterFromBottomExitToBottom(route = Screen.AddList.route) {
            CreateListScreen(navigator = appNavigator)
        }

        enterFromRightExitToRight(
            route = Screen.GameListDetail.route,
            arguments = listOf(navArgument("listId") { type = NavType.StringType })
        ) {
            val listId = it.arguments?.getString("listId")!!
            GameListDetailScreen(navigator = appNavigator, id = listId)
        }

        enterFromRightExitToRight(
            route = Screen.MovieListDetail.route,
            arguments = listOf(navArgument("listId") { type = NavType.StringType })
        ) {
            val listId = it.arguments?.getString("listId")!!
            MovieListDetailScreen(navigator = appNavigator, listId = listId)
        }

        enterFromBottomExitToBottom(
            route = Screen.AddGame.route,
            arguments = listOf(navArgument("parentId") { type = NavType.StringType })
        ) {
            val parentListId = it.arguments?.getString("parentId")!!
            AddGameScreen(navigator = appNavigator, parentListId = parentListId)
        }
    }
}