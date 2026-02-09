package dev.spozap.feature.favorites.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.spozap.feature.favorites.FavoritesScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data object FavoritesRouteKey

fun NavGraphBuilder.favoritesScreen() {
    composable<FavoritesRouteKey> {
        FavoritesScreenRoute()
    }
}

fun NavController.navigateToFavorites() {
    navigate(FavoritesRouteKey) {
        launchSingleTop = true
    }
}