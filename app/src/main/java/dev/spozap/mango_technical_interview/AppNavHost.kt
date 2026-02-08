package dev.spozap.mango_technical_interview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dev.spozap.profile.navigation.NavigationRouteKey
import dev.spozap.profile.navigation.profileRoute

@Composable
internal fun AppNavHost(navController: NavHostController) {
    Scaffold { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            NavHost(
                navController = navController,
                startDestination = NavigationRouteKey
            ) {
                profileRoute()
            }
        }
    }

}