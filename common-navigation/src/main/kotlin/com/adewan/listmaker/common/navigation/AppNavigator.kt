package com.adewan.listmaker.common.navigation

import androidx.navigation.NavController

interface AppNavigator


class AppNavigatorImpl(private val navController: NavController) : AppNavigator