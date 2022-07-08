@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)

package com.adewan.listmaker.ui.add.movies

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.adewan.listmaker.common.navigation.AppNavigator
import com.adewan.listmaker.models.TMDBMovie
import com.adewan.listmaker.ui.common.components.LoadingComponent

@Composable
fun MovieAddScreen(
    navigator: AppNavigator,
    id: String,
    viewModel: MovieAddViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var searchTerms by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(topBar = { MovieSearchTopBar(navigator = navigator) }) { padding ->
        Column(modifier = Modifier.padding(padding).padding(horizontal = 15.dp)) {
            Text(
                text = "Add movie",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 20.dp)
            )
            MovieSearchBar(value = searchTerms, updateValue = { searchTerms = it }) {
                viewModel.searchForMovie(searchTerms.text)
            }
            when (uiState) {
                is MovieAddState.Loading -> LoadingComponent()
                is MovieAddState.Result ->
                    MovieResultsComponent(state = (uiState as MovieAddState.Result)) {
                        viewModel.saveMovieIntoList(id, it)
                        navigator.popCurrentRoute()
                    }
            }
        }
    }
}

@Composable
private fun MovieResultsComponent(
    state: MovieAddState.Result,
    onGameSelected: (TMDBMovie) -> Unit
) {
    Column(modifier = Modifier.padding(top = 5.dp)) {
        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp.dp
        val imageWidth = (screenWidth - 20.dp) / 3
        val imageHeight = imageWidth.times(1.7f)

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(horizontal = 5.dp)
        ) {
            items(state.data.count()) {
                Column(
                    modifier = Modifier.clickable { onGameSelected(state.data[it]) },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val movie = state.data[it]
                    Image(
                        painter =
                            rememberAsyncImagePainter(
                                model = (movie.qualifiedPosterUrl ?: movie.qualifiedBackdropUrl)
                            ),
                        contentDescription = movie.name,
                        modifier =
                            Modifier.width(imageWidth)
                                .height(imageHeight)
                                .clip(RoundedCornerShape(15.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        movie.title ?: movie.name ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
private fun MovieSearchTopBar(navigator: AppNavigator) {
    SmallTopAppBar(
        navigationIcon = {
            Icon(
                Icons.Default.Close,
                contentDescription = "Close add list screen",
                modifier = Modifier.size(32.dp).clickable { navigator.popCurrentRoute() }
            )
        },
        title = {},
        modifier = Modifier.padding(start = 10.dp)
    )
}

@Composable
private fun MovieSearchBar(
    value: TextFieldValue,
    updateValue: (TextFieldValue) -> Unit,
    onSearch: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = value,
        onValueChange = { updateValue(it) },
        modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp),
        maxLines = 1,
        singleLine = true,
        label = { Text(text = "Search Movies") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "") },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions =
            KeyboardActions(
                onSearch = {
                    onSearch()
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
            )
    )
}
