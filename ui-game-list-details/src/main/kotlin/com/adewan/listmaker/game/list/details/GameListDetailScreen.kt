@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.adewan.listmaker.game.list.details

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.adewan.listmaker.common.navigation.AppNavigator
import com.adewan.listmaker.ui.common.components.EmptyListComponent
import com.adewan.listmaker.ui.common.components.FloatingActionComponent
import com.adewan.listmaker.ui.common.components.LoadingComponent
import com.adewan.listmaker.ui.common.components.ThemedContainerComponent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GameListDetailScreen(
    navigator: AppNavigator,
    id: String,
    viewModel: GameListDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = uiState) { viewModel.getListDetailsForId(id = id) }
    ThemedContainerComponent {
        when (uiState) {
            GameListDetailState.Loading -> LoadingComponent()
            is GameListDetailState.Result -> {
                val listState = uiState as GameListDetailState.Result
                ListDetails(navigator = navigator, state = listState, id = id)
            }
        }
    }
}

@Composable
private fun ListDetails(navigator: AppNavigator, state: GameListDetailState.Result, id: String) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { ListDetailTopBar(navigator = navigator, title = state.title) },
        floatingActionButton = {
            FloatingActionComponent(icon = Icons.Default.Search) {
                navigator.navigateToAddGameScreen(id = id)
            }
        }
    ) { paddingValues ->
        if (state.data.isEmpty()) {
            EmptyListComponent("You've not add any games yet!")
        } else {
            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp.dp
            val imageWidth = (screenWidth - 20.dp) / 3
            val imageHeight = imageWidth.times(1.7f)

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.padding(paddingValues).padding(horizontal = 5.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(state.data.size) {
                    val game = state.data[it]
                    Image(
                        painter = rememberAsyncImagePainter(model = game.posterUrl),
                        contentDescription = game.name,
                        modifier =
                            Modifier.width(imageWidth)
                                .height(imageHeight)
                                .clip(RoundedCornerShape(15.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Composable
private fun ListDetailTopBar(navigator: AppNavigator, title: String) {
    SmallTopAppBar(
        title = { Text(title, fontWeight = FontWeight.Bold) },
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
        colors =
            ButtonDefaults.filledTonalButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
    ) {
        Icon(
            Icons.Default.Search,
            contentDescription = "content description",
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}
