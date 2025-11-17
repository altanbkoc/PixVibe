package com.altankoc.pixvibe.presentation.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Home : Screen("home")
    data object Search : Screen("search")
    data object Favorites : Screen("favorites")
    data object ImageDetail : Screen("image_detail/{imageId}") {
        fun createRoute(imageId: Int) = "image_detail/$imageId"
    }
    data object FullScreenImage : Screen("full_screen_image/{imageId}") {
        fun createRoute(imageId: Int) = "full_screen_image/$imageId"
    }
}