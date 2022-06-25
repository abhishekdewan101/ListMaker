@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class,
    ExperimentalFoundationApi::class
)

package com.adewan.listmaker.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adewan.listmaker.common.navigation.AppNavigator
import com.adewan.listmaker.db.AppList
import com.adewan.listmaker.ui.common.ThemedContainer

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navigator: AppNavigator, viewModel: HomeScreenViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    ThemedContainer {
        Scaffold(floatingActionButton = { ListAdditionButton() }, topBar = { ListTopBar() }) {
            when (uiState) {
                is HomeScreenState.Loading -> LoadingUi()
                is HomeScreenState.EmptyList -> EmptyListUi()
                is HomeScreenState.ListPresent -> {
                    val state = uiState as HomeScreenState.ListPresent
                    ListUi(lists = state.lists, paddingValues = it)
                }
            }
        }
    }
}

@Composable
private fun ListUi(lists: List<AppList>, paddingValues: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .padding(top = 15.dp, start = 15.dp, end = 15.dp)
            .fillMaxSize()
    ) {

    }
}

@Composable
private fun EmptyListUi() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.empty_list),
            contentDescription = "empty list icon",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer),
            modifier = Modifier
                .size(128.dp)
                .padding(bottom = 15.dp)
        )
        Text("No lists have been created")
    }
}

@Composable
private fun LoadingUi() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ListTopBar() {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
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
            onValueChange = { searchText = it },
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
private fun ListAdditionButton() {
    FilledTonalButton(
        onClick = { },
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