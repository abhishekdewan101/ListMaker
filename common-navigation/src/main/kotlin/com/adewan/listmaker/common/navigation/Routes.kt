package com.adewan.listmaker.common.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object AddList : Screen("addList")
    object GameListDetail : Screen("gameListDetail/{listId}")
    object AddGame : Screen("addGame/{parentId}")
}