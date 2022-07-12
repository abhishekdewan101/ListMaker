package com.adewan.listmaker.ui.common.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ClickableMessage(@DrawableRes icon: Int, message: String, onClick: () -> Unit) {
    Column(
        modifier =
            Modifier.fillMaxSize()
                .padding(horizontal = 15.dp, vertical = 15.dp)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable { onClick() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "empty game list",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(48.dp).padding(top = 15.dp)
        )
        Text(message, modifier = Modifier.padding(top = 10.dp).padding(bottom = 15.dp))
    }
}
