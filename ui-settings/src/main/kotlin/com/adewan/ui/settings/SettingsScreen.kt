package com.adewan.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adewan.listmaker.common.navigation.AppNavigatorImpl
import com.adewan.listmaker.ui.common.ThemedContainer

@Composable
fun SettingsScreen(navigator: AppNavigatorImpl) {
    ThemedContainer {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Text("Hello From Settings Screen")
        }
    }
}