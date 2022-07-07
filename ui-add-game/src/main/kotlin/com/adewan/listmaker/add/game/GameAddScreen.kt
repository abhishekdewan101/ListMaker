@file:OptIn(ExperimentalMaterial3Api::class)

package com.adewan.listmaker.add.game

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.adewan.listmaker.common.navigation.AppNavigator
import com.adewan.listmaker.models.IGDBGame

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GameAddScreen(
    navigator: AppNavigator,
    parentListId: String,
    viewModel: GameAddViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var searchTerms by remember { mutableStateOf(TextFieldValue("")) }
    Scaffold(topBar = { GameSearchTopBar(navigator = navigator) }) { padding ->
        Column(modifier = Modifier.padding(padding).padding(horizontal = 15.dp)) {
            GameSearchBar(value = searchTerms, updateValue = { searchTerms = it }) {
                viewModel.searchForGame(searchTerms.text)
            }
            when (uiState) {
                is GameAddScreenState.Loading -> LoadingUiState()
                is GameAddScreenState.Result ->
                    ResultUiState(state = (uiState as GameAddScreenState.Result)) {
                        viewModel.saveGameIntoList(parentListId, it)
                        navigator.popCurrentRoute()
                    }
            }
        }
    }
}

@Composable
private fun ResultUiState(state: GameAddScreenState.Result, onGameSelected: (IGDBGame) -> Unit) {
    Column(modifier = Modifier.padding(top = 5.dp).padding(horizontal = 15.dp)) {
        Text(
            state.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(top = 15.dp)
        ) {
            items(state.data.count()) {
                Column(
                    modifier =
                        Modifier.fillMaxSize().padding(10.dp).clickable {
                            onGameSelected(state.data[it])
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val game = state.data[it]
                    Image(
                        painter = rememberAsyncImagePainter(model = game.coverImage!!.qualifiedUrl),
                        contentDescription = game.name,
                        modifier = Modifier.width(100.dp).height(175.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        game.name,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
private fun LoadingUiState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) { CircularProgressIndicator() }
}

@Composable
private fun GameSearchTopBar(navigator: AppNavigator) {
    SmallTopAppBar(
        title = {},
        modifier = Modifier.padding(start = 10.dp),
        navigationIcon = {
            Icon(
                Icons.Default.Close,
                contentDescription = "close",
                modifier = Modifier.size(32.dp).clickable { navigator.popCurrentRoute() }
            )
        }
    )
}

@Composable
private fun GameSearchBar(
    value: TextFieldValue,
    updateValue: (TextFieldValue) -> Unit,
    onSearch: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = { updateValue(it) },
        modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp),
        maxLines = 1,
        singleLine = true,
        label = { Text(text = "Search Games") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "") },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch() })
    )
}
