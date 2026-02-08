package dev.spozap.feature.products.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.spozap.feature.products.ProductsScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data object ProductsRouteKey

fun NavController.navigateToProducts() {
    navigate(ProductsRouteKey) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.productsScreen() {
    composable<ProductsRouteKey> {
        ProductsScreenRoute()
    }
}