package com.altankoc.pixvibe.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.altankoc.pixvibe.presentation.screens.detail.ImageDetailScreen
import com.altankoc.pixvibe.presentation.screens.favorites.FavoritesScreen
import com.altankoc.pixvibe.presentation.screens.fullscreen.FullScreenImageScreen
import com.altankoc.pixvibe.presentation.screens.home.HomeScreen
import com.altankoc.pixvibe.presentation.screens.search.SearchScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Screen.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                onImageClick = { imageId ->
                    navController.navigate(Screen.ImageDetail.createRoute(imageId))
                }
            )
        }

        composable(route = Screen.Search.route) {
            SearchScreen(
                onImageClick = { imageId ->
                    navController.navigate(Screen.ImageDetail.createRoute(imageId))
                }
            )
        }

        composable(route = Screen.Favorites.route) {
            FavoritesScreen(
                onImageClick = { imageId ->
                    navController.navigate(Screen.ImageDetail.createRoute(imageId))
                }
            )
        }

        composable(
            route = Screen.ImageDetail.route,
            arguments = listOf(
                navArgument("imageId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val imageId = backStackEntry.arguments?.getInt("imageId") ?: return@composable
            ImageDetailScreen(
                imageId = imageId,
                onBackClick = {
                    navController.navigateUp()
                },
                onImageClick = {
                    navController.navigate(Screen.FullScreenImage.createRoute(imageId))
                }
            )
        }

        composable(
            route = Screen.FullScreenImage.route,
            arguments = listOf(
                navArgument("imageId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val imageId = backStackEntry.arguments?.getInt("imageId") ?: return@composable
            FullScreenImageScreen(
                imageId = imageId,
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}