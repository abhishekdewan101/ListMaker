package com.adewan.listmaker.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.adewan.listmaker.common.navigation.AppNavigator
import com.adewan.listmaker.ui.common.ThemedContainer

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navigator: AppNavigator) {
    ThemedContainer {
        Scaffold(topBar = { TopBar(appNavigator = navigator) }) {

        }
    }

}

@Composable
private fun TopBar(appNavigator: AppNavigator) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            "ListMaker",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
        )
        AvatarImage(goToSettings = { appNavigator.navigateToSettings() })
    }
}

@Composable
private fun AvatarImage(goToSettings: () -> Unit) {
    IconButton(onClick = goToSettings) {
        Icon(
            painter = rememberVectorPainter(image = Icons.Outlined.Face),
            modifier = Modifier.size(32.dp),
            contentDescription = "User"
        )
    }
}