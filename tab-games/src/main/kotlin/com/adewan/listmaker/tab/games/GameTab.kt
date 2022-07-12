@file:OptIn(ExperimentalMaterial3Api::class)

package com.adewan.listmaker.tab.games

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.adewan.listmaker.common.navigation.AppNavigator
import com.adewan.listmaker.ui.common.components.OutlinedSearchTextField

@Composable
fun GameTab(appNavigator: AppNavigator) {
    var searchTextValue by remember { mutableStateOf(TextFieldValue("")) }
    Scaffold { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(top = 15.dp)) {
            OutlinedSearchTextField(
                searchValue = searchTextValue,
                placeholder = "Filter games lists...",
                updateValue = { searchTextValue = it }
            ) {
                // TODO: Filter games list
            }
        }
    }
}
