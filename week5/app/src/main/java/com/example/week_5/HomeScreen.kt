package com.example.week_5

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class Article(
    val id: Int,
    val title: String
)

val dummyArticles = listOf(
    Article(1, "Belajar Jetpack Compose"),
    Article(2, "Navigation pada Android"),
    Article(3, "Mengenal LazyColumn"),
    Article(4, "Membuat Bottom Navigation"),
    Article(5, "Argument antar Screen")
)

@Composable
fun HomeScreen(
    navController: NavController
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(dummyArticles) { article ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .clickable {
                        navController.navigate(
                            Routes.Detail.createRoute(article.id)
                        )
                    }
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = article.title,
                        fontSize = 18.sp
                    )

                    Text(
                        text = "Artikel #${article.id}",
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }
            }
        }
    }
}