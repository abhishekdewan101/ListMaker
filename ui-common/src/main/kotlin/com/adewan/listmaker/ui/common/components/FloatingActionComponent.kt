package com.adewan.listmaker.ui.common.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun FloatingActionComponent(icon: ImageVector, onPress: () -> Unit) {
    FilledTonalButton(
        onClick = onPress,
        modifier = Modifier.size(64.dp),
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp),
        colors =
            ButtonDefaults.filledTonalButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
    ) {
        Icon(
            icon,
            contentDescription = "content description",
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}
