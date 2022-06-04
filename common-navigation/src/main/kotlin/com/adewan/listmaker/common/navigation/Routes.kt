package com.adewan.listmaker.common.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
}