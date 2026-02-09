package dev.spozap.feature.favorites

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.spozap.core.model.Product
import dev.spozap.core.model.ProductRating
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private val fakeProducts = listOf(
    Product(
        id = 1,
        title = "Test Product",
        description = "description",
        price = 2.0,
        category = "Category",
        image = "https://www.google.com",
        rating = ProductRating(
            rate = 5.0,
            count = 200
        )
    ), Product(
        id = 2,
        title = "Test Product 2",
        description = "description",
        price = 2.0,
        category = "Category",
        image = "https://www.google.com",
        rating = ProductRating(
            rate = 5.0,
            count = 200
        )
    )
)

@RunWith(AndroidJUnit4::class)
class FavoritesScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loading_showsLoadingSpinner() {
        composeTestRule.setContent {
            FavoritesScreen(uiState = FavoritesUiState.Loading)
        }

        composeTestRule.onNodeWithTag("loadingWheel").assertExists()
    }

    @Test
    fun empty_uiState_shows_text_placeholder() {
        composeTestRule.setContent {
            FavoritesScreen(uiState = FavoritesUiState.Empty)
        }

        composeTestRule.onNodeWithText("No hay productos añadidos")
            .assertExists()
    }

    @Test
    fun success_uiState_shows_products_list() {
        composeTestRule.setContent {
            FavoritesScreen(uiState = FavoritesUiState.Success(products = fakeProducts))
        }

        composeTestRule.onAllNodesWithTag("productCard")
            .assertCountEquals(fakeProducts.size)
    }

    @Test
    fun error_uiState_displays_error_text() {
        composeTestRule.setContent {
            FavoritesScreen(uiState = FavoritesUiState.Error(error = ""))
        }

        composeTestRule.onNodeWithText("ERROR")
            .assertExists()
    }
}