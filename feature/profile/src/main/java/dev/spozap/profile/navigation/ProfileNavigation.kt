package dev.spozap.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.spozap.profile.ProfileScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data object ProfileRouteKey

fun NavController.navigateToProfile() {
    navigate(route = ProfileRouteKey)
}

fun NavGraphBuilder.profileRoute(onNavigateToFavorites: () -> Unit) {
    composable<ProfileRouteKey> {
        ProfileScreenRoute(onNavigateToFavorites = onNavigateToFavorites)
    }
}