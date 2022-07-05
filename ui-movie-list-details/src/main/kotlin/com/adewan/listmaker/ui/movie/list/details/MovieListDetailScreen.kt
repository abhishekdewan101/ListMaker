@file:OptIn(ExperimentalMaterial3Api::class)

package com.adewan.listmaker.ui.movie.list.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adewan.listmaker.common.navigation.AppNavigator

@Composable
fun MovieListDetailScreen(navigator: AppNavigator, listId: String) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(it)) {
            Text(text = "Movie Detail Screen $listId")
        }
    }

}