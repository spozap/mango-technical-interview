package dev.spozap.feature.products

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithContentDescription
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
        ),
        isFavourite = true
    )
)

@RunWith(AndroidJUnit4::class)
class ProductsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loading_uiState_shows_spinner() {
        composeTestRule.setContent {
            ProductsScreen(uiState = ProductsUiState.Loading, onAddToFavourites = {})
        }

        composeTestRule.onNodeWithTag("loadingWheel").assertExists()
    }

    @Test
    fun error_uiState_shows_error_placeholder() {
        composeTestRule.setContent {
            ProductsScreen(uiState = ProductsUiState.Error(""), onAddToFavourites = {})
        }

        composeTestRule.onNodeWithText("ERROR").assertExists()
    }

    @Test
    fun success_uiState_shows_product_card_list() {
        composeTestRule.setContent {
            ProductsScreen(uiState = ProductsUiState.Success(fakeProducts), onAddToFavourites = {})
        }

        composeTestRule.onAllNodesWithTag("productCard")
            .assertCountEquals(fakeProducts.size)
    }

    @Test
    fun product_marked_as_favorite_has_favorite_content_description() {
        composeTestRule.setContent {
            ProductsScreen(uiState = ProductsUiState.Success(fakeProducts), onAddToFavourites = {})
        }

        composeTestRule.onNodeWithContentDescription("Producto favorito ${fakeProducts[1].title}")
            .assertExists()
    }

    @Test
    fun product_marked_as_non_favorite_has_non_favorite_content_description() {
        composeTestRule.setContent {
            ProductsScreen(uiState = ProductsUiState.Success(fakeProducts), onAddToFavourites = {})
        }

        composeTestRule.onNodeWithContentDescription("Producto no favorito")
            .assertExists()
    }
}