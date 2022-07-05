package com.adewan.listmaker.ui.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.adewan.listmaker.ui.common.R

@Composable
fun EmptyListComponent(message: String) {
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
        Text(message)
    }
}