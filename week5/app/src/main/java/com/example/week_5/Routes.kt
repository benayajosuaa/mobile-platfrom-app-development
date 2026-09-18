package com.example.week_5

sealed class Routes(val route: String) {

    data object Home : Routes("home")

    data object Explore : Routes("explore")

    data object Profile : Routes("profile")

    data object Detail : Routes("detail/{itemId}") {
        fun createRoute(itemId: Int): String {
            return "detail/$itemId"
        }
    }
}