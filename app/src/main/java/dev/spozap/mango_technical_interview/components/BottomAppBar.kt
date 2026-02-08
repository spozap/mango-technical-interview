package dev.spozap.mango_technical_interview.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import dev.spozap.mango_technical_interview.navigation.TOP_LEVEL_NAVIGATION_ITEMS

@Composable
internal fun BottomAppBar(navController: NavController) {
    NavigationBar {
        TOP_LEVEL_NAVIGATION_ITEMS.forEach { (route, navigationItem) ->
            NavigationBarItem(
                selected = false,
                onClick = {
                    navController.navigate(route)
                },
                label = { Text(text = navigationItem.text) },
                icon = {
                    Icon(
                        imageVector = navigationItem.icon,
                        contentDescription = "Icon"
                    )
                }
            )
        }
    }
}