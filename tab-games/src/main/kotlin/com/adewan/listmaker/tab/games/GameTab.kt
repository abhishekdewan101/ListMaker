@file:OptIn(ExperimentalMaterial3Api::class)

package com.adewan.listmaker.tab.games

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adewan.listmaker.common.navigation.AppNavigator
import com.adewan.listmaker.database.CoreList
import com.adewan.listmaker.database.CoreListType
import com.adewan.listmaker.database.GameListEntry
import com.adewan.listmaker.ui.common.components.ClickableMessage
import com.adewan.listmaker.ui.common.components.FullScreenMessageComponent
import com.adewan.listmaker.ui.common.components.HorizontalImageList
import com.adewan.listmaker.ui.common.components.LoadingComponent
import com.adewan.listmaker.ui.common.components.OutlinedSearchTextField
import com.adewan.listmaker.ui.tabs.games.R
import java.util.UUID
import kotlinx.coroutines.launch

@Composable
fun GameTab(viewModel: GameTabViewModel = hiltViewModel(), appNavigator: AppNavigator) {
    val uiState by viewModel.uiState.collectAsState()

    var searchTextValue by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(floatingActionButton = { CreateListButton(appNavigator = appNavigator) }) {
        paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(top = 15.dp)) {
            OutlinedSearchTextField(
                searchValue = searchTextValue,
                placeholder = "Filter games lists...",
                updateValue = { searchTextValue = it }
            ) {}
            when (uiState) {
                is GameTabState.Loading -> LoadingComponent()
                is GameTabState.Empty ->
                    FullScreenMessageComponent(message = "Tap to create your first list") {
                        appNavigator.navigateToAddListScreen()
                    }
                is GameTabState.Success -> {
                    val state = uiState as GameTabState.Success
                    val filteredLists =
                        if (searchTextValue.text.isEmpty()) {
                            state.data
                        } else {
                            state.data.filter {
                                it.title.contains(searchTextValue.text, ignoreCase = true)
                            }
                        }
                    GamesList(data = filteredLists, appNavigator = appNavigator) {
                        viewModel.getGamesForList(it)
                    }
                }
            }
        }
    }
}

@Composable
private fun CreateListButton(appNavigator: AppNavigator) {
    FloatingActionButton(onClick = { appNavigator.navigateToAddListScreen() }) {
        Icon(Icons.Outlined.Add, contentDescription = "add list")
    }
}

@Composable
private fun GamesList(
    data: List<CoreList>,
    appNavigator: AppNavigator,
    searchForGames: suspend (UUID) -> List<GameListEntry>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(data.size) {
            GameListPreview(
                data = data[it],
                appNavigator = appNavigator,
                searchForGames = searchForGames
            )
        }
    }
}

@Composable
private fun GameListPreview(
    data: CoreList,
    appNavigator: AppNavigator,
    searchForGames: suspend (UUID) -> List<GameListEntry>
) {
    var gamesForList by remember { mutableStateOf<List<GameListEntry>>(emptyList()) }
    val scope = rememberCoroutineScope()
    SideEffect { scope.launch { gamesForList = searchForGames(data.id) } }
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(data.title, style = MaterialTheme.typography.headlineSmall)
        Text(
            "VIEW ALL",
            style =
                MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                ),
            modifier =
                Modifier.clickable {
                    appNavigator.navigateToListDetailScreen(id = data.id, CoreListType.GAMES)
                }
        )
    }
    if (gamesForList.isEmpty()) {
        ClickableMessage(icon = R.drawable.heart, message = "Tap to add games") {
            appNavigator.navigateToAddGameScreen(data.id.toString())
        }
    } else {
        HorizontalImageList(modifier = Modifier.padding(top = 10.dp), data = gamesForList)
    }
}
