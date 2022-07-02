@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.adewan.listmaker.list.details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adewan.listmaker.common.navigation.AppNavigator
import com.adewan.listmaker.ui.common.EmptyListScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListDetailsScreen(
    navigator: AppNavigator,
    id: String,
    viewModel: ListDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = uiState) {
        viewModel.getListDetailsForId(id = id)
    }

    when (uiState) {
        ListDetailUiState.Loading -> LoadingScreen()
        is ListDetailUiState.ListDetailState -> {
            val listState = uiState as ListDetailUiState.ListDetailState
            ListDetails(navigator = navigator, state = listState, id = id)
        }
    }
}

@Composable
private fun ListDetails(
    navigator: AppNavigator,
    state: ListDetailUiState.ListDetailState,
    id: String
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = { ListDetailTopBar(navigator = navigator, title = state.listName) },
        floatingActionButton = { AddGameButton(navigator = navigator, parentId = id) }
    ) {
        if (state.games.isEmpty()) {
            EmptyListScreen("You've not add any games yet!")
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(horizontal = 15.dp)
            ) {
                
            }
        }
    }
}

@Composable
private fun LoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ListDetailTopBar(navigator: AppNavigator, title: String) {
    SmallTopAppBar(title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { navigator.popCurrentRoute() }) {
                Icon(
                    Icons.Default.KeyboardArrowLeft,
                    contentDescription = "close list details",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    )
}

@Composable
private fun AddGameButton(navigator: AppNavigator, parentId: String) {
    FilledTonalButton(
        onClick = { navigator.navigateToAddGameScreen(id = parentId) },
        modifier = Modifier.size(64.dp),
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.filledTonalButtonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Icon(
            Icons.Default.Search,
            contentDescription = "content description",
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}