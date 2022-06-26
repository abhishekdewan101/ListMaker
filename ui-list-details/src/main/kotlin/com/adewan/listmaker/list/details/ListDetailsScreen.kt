@file:OptIn(ExperimentalMaterial3Api::class)

package com.adewan.listmaker.list.details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adewan.listmaker.common.navigation.AppNavigator

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListDetailsScreen(navigator: AppNavigator, id: String) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Text("List details for $id")
    }
}