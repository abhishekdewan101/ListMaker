package com.adewan.listmaker.ui.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.adewan.listmaker.common.navigation.AppNavigator
import com.adewan.listmaker.ui.common.ThemedContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navigator: AppNavigator, viewModel: HomeScreenViewModel = hiltViewModel()) {
    ThemedContainer {
        Scaffold {

        }
    }
}