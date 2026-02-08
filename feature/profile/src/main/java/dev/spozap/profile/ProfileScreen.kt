package dev.spozap.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import dev.spozap.profile.components.UserProfileData

@Composable
internal fun ProfileScreenRoute(viewModel: ProfileViewModel = hiltViewModel()) {
    val profileUiState by viewModel.profileUiState.collectAsStateWithLifecycle()
    ProfileScreen(uiState = profileUiState)
}

@Composable
fun ProfileScreen(
    uiState: ProfileUIState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 24.dp)
    ) {
        when (uiState) {
            is ProfileUIState.Success -> item { ProfileScreenSuccess(uiState.profile) }

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
fun ProfileScreenSuccess(user: User, modifier: Modifier = Modifier) {
    UserProfileData(user, modifier)
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
        LoadingWheel(modifier = Modifier.testTag("ProfileLoading"))
    }
}


@Preview
@Composable
private fun ProfileScreenSuccessPreview() {
    ProfileScreen(
        uiState = ProfileUIState.Success(
            User(
                id = "1",
                username = "Prueba",
                email = "prueba@gmail.com",
                phone = "+34 655555555"
            )
        )
    )
}