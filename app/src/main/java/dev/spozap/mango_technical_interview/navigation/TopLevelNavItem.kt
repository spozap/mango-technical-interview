package dev.spozap.mango_technical_interview.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import dev.spozap.profile.navigation.ProfileRouteKey

data class TopLevelNavItem(
    val icon: ImageVector,
    val text: String
)

val PROFILE = TopLevelNavItem(
    icon = Icons.Default.Person,
    text = "Perfil"
)

val TOP_LEVEL_NAVIGATION_ITEMS = mapOf(
    ProfileRouteKey to PROFILE
)

