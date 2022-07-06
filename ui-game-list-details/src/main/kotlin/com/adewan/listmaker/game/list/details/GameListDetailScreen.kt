@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.adewan.listmaker.game.list.details

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.adewan.listmaker.common.navigation.AppNavigator
import com.adewan.listmaker.ui.common.components.EmptyListComponent
import kotlin.math.roundToInt

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GameListDetailScreen(
    navigator: AppNavigator,
    id: String,
    viewModel: GameListDetailViewModel = hiltViewModel()
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
            EmptyListComponent("You've not add any items yet!")
        } else {
            LazyColumn(
                modifier = Modifier.padding(it)
            ) {
                items(state.games.count()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                            .padding(horizontal = 10.dp)
                    ) {
                        val game = state.games[it]
                        Image(
                            painter = rememberAsyncImagePainter(model = game.posterUrl),
                            contentDescription = game.name,
                            modifier = Modifier
                                .width(125.dp)
                                .height(200.dp)
                                .clip(RoundedCornerShape(15.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 5.dp),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                game.name,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Start,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                game.summary,
                                style = MaterialTheme.typography.bodySmall,
                                textAlign = TextAlign.Start,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp),
                                maxLines = 5,
                                overflow = TextOverflow.Ellipsis
                            )

                            Text(
                                "${game.rating.roundToInt()} / 100",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Start,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                    Divider(modifier = Modifier.padding(horizontal = 10.dp))
                }
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