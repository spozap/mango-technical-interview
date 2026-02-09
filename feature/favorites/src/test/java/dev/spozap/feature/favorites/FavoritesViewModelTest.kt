package dev.spozap.feature.favorites

import app.cash.turbine.test
import dev.spozap.core.data.repository.FavoriteProductsRepository
import dev.spozap.core.model.Product
import dev.spozap.core.model.ProductRating
import dev.spozap.core.testing.MainDispatcherRule
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

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

class FavoritesViewModelTest {

    @get:Rule
    val dispatchersRule = MainDispatcherRule()

    private lateinit var favoritesProductsRepository: FavoriteProductsRepository

    @Before
    fun setup() {
        favoritesProductsRepository = mockk()
    }

    @Test
    fun `initial FavoritesUiState is Loading`() = runTest {
        every { favoritesProductsRepository.favoriteProducts } returns emptyFlow()

        val viewModel = FavoritesViewModel(favoritesProductsRepository)

        viewModel.products.test {
            assertEquals(FavoritesUiState.Loading, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `favorites UiState is Success when repository emits products`() = runTest {
        val fakeFavorites = fakeProducts
        every { favoritesProductsRepository.favoriteProducts } returns flowOf(fakeFavorites)

        val viewModel = FavoritesViewModel(favoritesProductsRepository)

        viewModel.products.test {
            val success = awaitItem()
            assertTrue(success is FavoritesUiState.Success)
            assertEquals(fakeFavorites, (success as FavoritesUiState.Success).products)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `favorites UiState is Empty when repository emits empty list`() = runTest {
        every { favoritesProductsRepository.favoriteProducts } returns flowOf(emptyList())

        val viewModel = FavoritesViewModel(favoritesProductsRepository)

        viewModel.products.test {
            assertEquals(FavoritesUiState.Empty, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `favorites UiState is Error when repository throws exception`() = runTest {
        every { favoritesProductsRepository.favoriteProducts } returns flow { throw Exception() }

        val viewModel = FavoritesViewModel(favoritesProductsRepository)

        viewModel.products.test {
            val error = awaitItem()
            assertTrue(error is FavoritesUiState.Error)
            assertEquals(
                "Ha ocurrido un error al cargar los productos favoritos",
                (error as FavoritesUiState.Error).error
            )
            cancelAndIgnoreRemainingEvents()
        }
    }


}