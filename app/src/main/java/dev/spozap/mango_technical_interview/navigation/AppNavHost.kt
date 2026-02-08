package dev.spozap.mango_technical_interview.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dev.spozap.feature.products.navigation.productsScreen
import dev.spozap.mango_technical_interview.components.BottomAppBar
import dev.spozap.profile.navigation.ProfileRouteKey
import dev.spozap.profile.navigation.profileRoute

@Composable
internal fun AppNavHost(navController: NavHostController) {
    Scaffold(
        bottomBar = { BottomAppBar(navController) }
    ) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            NavHost(
                navController = navController,
                startDestination = ProfileRouteKey
            ) {
                profileRoute()
                productsScreen()
            }
        }
    }

}