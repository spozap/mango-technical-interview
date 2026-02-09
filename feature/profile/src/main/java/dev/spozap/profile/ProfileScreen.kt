package dev.spozap.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.spozap.core.model.User
import dev.spozap.core.ui.components.LoadingWheel
import dev.spozap.core.ui.previews.USER_PREVIEW
import dev.spozap.design_system.MangotechnicalinterviewTheme
import dev.spozap.profile.components.FavoriteProductsCounter
import dev.spozap.profile.components.UserProfileData

@Composable
internal fun ProfileScreenRoute(
    viewModel: ProfileViewModel = hiltViewModel(),
    onNavigateToFavorites: () -> Unit
) {
    val profileUiState by viewModel.profileUiState.collectAsStateWithLifecycle()
    val favoriteProductsCount by viewModel.favoriteProductsCount.collectAsStateWithLifecycle()

    ProfileScreen(
        uiState = profileUiState,
        favoriteProductsCount = favoriteProductsCount,
        onNavigateToFavorites = onNavigateToFavorites
    )
}

@Composable
fun ProfileScreen(
    uiState: ProfileUIState,
    favoriteProductsCount: Int,
    onNavigateToFavorites: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 24.dp)
    ) {
        when (uiState) {
            is ProfileUIState.Success -> item {
                ProfileScreenSuccess(
                    user = uiState.profile,
                    favouritesCount = favoriteProductsCount,
                    onNavigateToFavorites = onNavigateToFavorites
                )
            }

            is ProfileUIState.Loading -> item {
                ProfileScreenLoading(modifier = Modifier.fillParentMaxSize())
            }

            is ProfileUIState.Error -> item {
                ProfileScreenError(modifier = Modifier.fillParentMaxSize())
            }
        }
    }
}

@Composable
fun ProfileScreenSuccess(
    user: User,
    favouritesCount: Int,
    onNavigateToFavorites: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(verticalArrangement = Arrangement.spacedBy(32.dp)) {
        UserProfileData(user, modifier)
        FavoriteProductsCounter(favouritesCount, onNavigateToFavorites)
    }
}

@Composable
fun ProfileScreenError(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text("ERROR", modifier = Modifier.testTag("ProfileError"))
    }
}

@Composable
fun ProfileScreenLoading(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        LoadingWheel()
    }
}

//region Previews

@Preview(showBackground = true)
@Composable
private fun ProfileScreenSuccessPreview() {
    MangotechnicalinterviewTheme {
        ProfileScreen(
            uiState = ProfileUIState.Success(USER_PREVIEW),
            favoriteProductsCount = 10,
            onNavigateToFavorites = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenErrorPreview() {
    MangotechnicalinterviewTheme {
        ProfileScreen(
            uiState = ProfileUIState.Error(""),
            favoriteProductsCount = 10,
            onNavigateToFavorites = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenLoadingPreview() {
    MangotechnicalinterviewTheme {
        ProfileScreen(
            uiState = ProfileUIState.Loading,
            favoriteProductsCount = 10,
            onNavigateToFavorites = {}
        )
    }
}

//endregion