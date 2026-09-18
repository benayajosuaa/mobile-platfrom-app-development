package com.example.week_5

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class Photo(
    val id: Int,
    val color: Color
)

val dummyPhotos = (1..20).map { i ->
    Photo(
        id = i,
        color = Color(
            red = (i * 37 % 256) / 255f,
            green = (i * 91 % 256) / 255f,
            blue = (i * 53 % 256) / 255f
        )
    )
}

@Composable
fun ExploreScreen() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = dummyPhotos,
            key = { it.id }
        ) { photo ->

            androidx.compose.foundation.layout.Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .background(photo.color)
            )
        }
    }
}