@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.adewan.listmaker.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adewan.listmaker.common.navigation.AppNavigator
import com.adewan.listmaker.db.Collection
import com.adewan.listmaker.ui.common.capitalize
import com.adewan.listmaker.ui.common.components.EmptyListComponent
import com.adewan.listmaker.ui.common.components.LoadingComponent
import com.adewan.listmaker.ui.common.components.ThemedContainerComponent
import java.util.UUID

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navigator: AppNavigator, viewModel: HomeScreenViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    ThemedContainerComponent {
        Scaffold(
            floatingActionButton = { CreateListButton(navigator = navigator) },
            topBar = {
                ListTopBar(searchText = searchText) {
                    searchText = it
                }
            }) { paddingValues ->
            when (uiState) {
                is HomeScreenState.Loading -> LoadingComponent()
                is HomeScreenState.Empty -> EmptyListComponent(message = "You've not created any lists yet!")
                is HomeScreenState.Result -> {
                    val state = uiState as HomeScreenState.Result
                    val filteredLists =
                        if (searchText.text.isBlank()) {
                            state.data
                        } else {
                            state.data.filter { it.title?.contains(searchText.text) ?: false }
                        }
                    HomeScreenListComponent(
                        lists = filteredLists,
                        paddingValues = paddingValues,
                        navigator = navigator
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeScreenListComponent(
    lists: List<Collection>,
    paddingValues: PaddingValues,
    navigator: AppNavigator
) {
    val groupedList = lists.groupBy { it.type }
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .padding(top = 15.dp, start = 15.dp, end = 15.dp)
            .fillMaxSize()
    ) {
        groupedList.filter { it.key != null }.forEach { (listType, typeLists) ->
            stickyHeader {
                Text(
                    capitalize(listType!!),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
            items(typeLists.size) {
                val item = typeLists[it]
                Card(
                    onClick = {
                        navigator.navigateToListDetailScreen(
                            UUID.fromString(item.id),
                            item.type!!
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(75.dp)
                        .padding(bottom = 10.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 10.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = item.title!!,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                            Icon(Icons.Default.KeyboardArrowRight, contentDescription = null)
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun ListTopBar(searchText: TextFieldValue, updateSearchText: (TextFieldValue) -> Unit) {
    val focusManger = LocalFocusManager.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 15.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = searchText,
            placeholder = { Text("Search lists...") },
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "search lists icon",
                )
            },
            onValueChange = { updateSearchText(it) },
            modifier = Modifier
                .fillMaxWidth(),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                focusManger.clearFocus(force = true)
            })
        )
    }
}

@Composable
private fun CreateListButton(navigator: AppNavigator) {
    FilledTonalButton(
        onClick = { navigator.navigateToAddListScreen() },
        modifier = Modifier.size(64.dp),
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.filledTonalButtonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Icon(
            Icons.Default.Add,
            contentDescription = "content description",
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}