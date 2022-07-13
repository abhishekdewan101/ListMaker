@file:OptIn(ExperimentalMaterial3Api::class)

package com.adewan.listmaker.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.adewan.listmaker.common.navigation.AppNavigator
import com.adewan.listmaker.tab.games.GameTab
import com.adewan.listmaker.ui.common.components.ThemedContainerComponent
import com.adewan.listmaker.ui.common.components.UnderConstructionComponent

@Composable
fun Home(appNavigator: AppNavigator) {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    ThemedContainerComponent {
        Scaffold(bottomBar = { HomeBottomBar(navController, currentBackStackEntry) }) {
            paddingValues ->
            Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                HomePager(navController = navController, appNavigator = appNavigator)
            }
        }
    }
}

@Composable
private fun HomeBottomBar(
    navController: NavHostController,
    currentBackStackEntry: NavBackStackEntry?
) {
    val selectedColor = MaterialTheme.colorScheme.primary
    val unSelectedColor = MaterialTheme.colorScheme.onBackground
    val currentSelectedRoute = currentBackStackEntry?.destination?.route
    NavigationBar(containerColor = Color.Transparent) {
        allTabs.forEach {
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = it.iconId),
                        contentDescription = null,
                        tint =
                            if (it.route == currentSelectedRoute) selectedColor else unSelectedColor
                    )
                },
                label = {
                    Text(
                        it.name,
                        fontWeight =
                            if (it.route == currentSelectedRoute) FontWeight.Bold
                            else FontWeight.Normal,
                        color =
                            if (it.route == currentSelectedRoute) selectedColor else unSelectedColor
                    )
                },
                selected = false,
                onClick = { navController.navigate(it.route) }
            )
        }
    }
}

@Composable
private fun HomePager(navController: NavHostController, appNavigator: AppNavigator) {
    NavHost(navController = navController, startDestination = HomeTabs.Games.route) {
        composable(HomeTabs.Games.route) { GameTab(appNavigator = appNavigator) }
        composable(HomeTabs.Movies.route) { UnderConstructionComponent() }
        composable(HomeTabs.Notes.route) { UnderConstructionComponent() }
        composable(HomeTabs.Profile.route) { UnderConstructionComponent() }
    }
}
