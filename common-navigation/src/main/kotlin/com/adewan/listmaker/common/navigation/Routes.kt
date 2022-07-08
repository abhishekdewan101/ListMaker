package com.adewan.listmaker.common.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object AddList : Screen("addList")
    object GameListDetail : Screen("gameListDetail/{listId}")
    object MovieListDetail : Screen("movieListDetail/{listId}")
    object AddGame : Screen("addGame/{parentId}")
    object AddMovie : Screen("addMovie/{parentId}")
}
