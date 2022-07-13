package com.adewan.listmaker.ui.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

data class HorizontalImageListEntry(val url: String, val name: String)

@Composable
fun HorizontalImageList(modifier: Modifier = Modifier, data: List<HorizontalImageListEntry>) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(data.size) {
            val entry = data[it]
            Image(
                painter = rememberAsyncImagePainter(model = entry.url),
                contentDescription = entry.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.width(125.dp).height(225.dp).clip(RoundedCornerShape(8.dp))
            )
        }
    }
}
