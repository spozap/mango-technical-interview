package dev.spozap.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.spozap.profile.ProfileScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data object NavigationRouteKey

fun NavController.navigateToProfile() {
    navigate(route = NavigationRouteKey)
}

fun NavGraphBuilder.profileRoute() {
    composable<NavigationRouteKey> {
        ProfileScreenRoute()
    }
}