package com.adewan.listmaker.ui.common.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ThemedContainerComponent(content: @Composable () -> Unit) {
    val controller = rememberSystemUiController()
    val useDarkIcon = !isSystemInDarkTheme()
    val systemBarColor = MaterialTheme.colorScheme.background

    SideEffect {
        controller.setStatusBarColor(color = systemBarColor, darkIcons = useDarkIcon)
        controller.setNavigationBarColor(color = systemBarColor, darkIcons = useDarkIcon)
    }

    content()
}
