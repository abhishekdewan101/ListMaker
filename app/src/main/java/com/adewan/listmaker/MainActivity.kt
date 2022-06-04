package com.adewan.listmaker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.adewan.listmaker.navigation.AppNavGraph
import com.adewan.listmaker.ui.theme.ListMakerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListMakerTheme {
                AppNavGraph()
            }
        }
    }
}