@file:OptIn(ExperimentalMaterial3Api::class)

package com.adewan.listmaker.add.game

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddGameScreen() {
    Scaffold {
        Column(modifier = Modifier.padding(it)) {
            Text("Add Game Screen")
        }
    }
}