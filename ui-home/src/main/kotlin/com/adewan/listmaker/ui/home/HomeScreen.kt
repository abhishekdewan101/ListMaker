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
        Scaffold(topBar = { TopBar() }) {

        }
    }

}

@Composable
private fun TopBar() {
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
        AvatarImage()
    }
}

@Composable
private fun AvatarImage() {
    IconButton(onClick = { /*TODO*/ }) {
        Icon(
            painter = rememberVectorPainter(image = Icons.Outlined.Face),
            modifier = Modifier.size(32.dp),
            contentDescription = "User"
        )
    }
}