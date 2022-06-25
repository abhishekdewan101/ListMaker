@file:OptIn(ExperimentalMaterial3Api::class)

package com.adewan.listmaker.list.add

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.adewan.listmaker.common.navigation.AppNavigatorImpl

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddListScreen(navigator: AppNavigatorImpl) {
    Scaffold {
        Text("Add List Screen")
    }
}