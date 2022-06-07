package com.adewan.ui.settings

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.adewan.listmaker.common.navigation.AppNavigatorImpl
import com.adewan.listmaker.ui.common.ThemedContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navigator: AppNavigatorImpl) {
    ThemedContainer {
        Scaffold {
            Text("Hello From Settings Screen")
        }
    }
}