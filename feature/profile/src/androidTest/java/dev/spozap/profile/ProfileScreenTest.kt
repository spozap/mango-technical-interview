package dev.spozap.profile

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.spozap.core.model.User
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProfileScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loading_showsLoadingSpinner() {
        composeTestRule.setContent {
            ProfileScreen(
                uiState = ProfileUIState.Loading,
                favoriteProductsCount = 0,
                onNavigateToFavorites = {}
            )
        }

        composeTestRule.onNodeWithTag("loadingWheel").assertExists()
    }

    @Test
    fun error_showsTextDisplayingError() {
        composeTestRule.setContent {
            ProfileScreen(
                uiState = ProfileUIState.Error("Example error"),
                favoriteProductsCount = 0,
                onNavigateToFavorites = {}
            )
        }

        composeTestRule.onNodeWithText("ERROR").assertIsDisplayed()
    }

    @Test
    fun success_ShowsFavouriteProductsCount() {
        val productsCount = 12
        composeTestRule.setContent {
            ProfileScreen(
                uiState = ProfileUIState.Success(
                    User(
                        id = "1",
                        username = "spozap",
                        email = "test@test.com",
                        phone = "+34 655555555"
                    )
                ),
                favoriteProductsCount = productsCount,
                onNavigateToFavorites = {}
            )
        }

        composeTestRule.onNodeWithText("Favoritos (${productsCount})")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun success_DisplaysProfileData() {
        val user = User(
            id = "1",
            username = "spozap",
            email = "test@test.com",
            phone = "+34 655555555"
        )

        composeTestRule.setContent {
            ProfileScreen(
                uiState = ProfileUIState.Success(user),
                favoriteProductsCount = 1,
                onNavigateToFavorites = {}
            )
        }

        composeTestRule.onNodeWithContentDescription("Imágen perfil usuario")
            .assertIsDisplayed()
            .assertExists()

        composeTestRule.onNodeWithText(user.username).assertExists()
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(user.email).assertExists()
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(user.phone).assertExists()
            .assertIsDisplayed()

    }

}