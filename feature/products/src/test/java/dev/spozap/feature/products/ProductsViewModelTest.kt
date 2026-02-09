package dev.spozap.feature.products

import app.cash.turbine.test
import dev.spozap.core.data.repository.FavoriteProductsRepository
import dev.spozap.core.data.repository.ProductsRepository
import dev.spozap.core.model.Product
import dev.spozap.core.model.ProductRating
import dev.spozap.core.testing.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
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

class ProductsViewModelTest {

    @get:Rule
    private val dispatchersRule = MainDispatcherRule()

    private lateinit var productsRepository: ProductsRepository
    private lateinit var favoriteProductsRepository: FavoriteProductsRepository

    private lateinit var viewModel: ProductsViewModel

    @Before
    fun setup() {
        productsRepository = mockk()
        favoriteProductsRepository = mockk()
    }

    @Test
    fun `initial ProductsUiState is Loading`() = runTest {
        every { productsRepository.getProducts() } returns emptyFlow()

        viewModel = ProductsViewModel(productsRepository, favoriteProductsRepository)

        viewModel.products.test {
            assertEquals(ProductsUiState.Loading, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }

    }

    @Test
    fun `when products are loaded UiState is Success`() = runTest {
        every { productsRepository.getProducts() } returns flowOf(fakeProducts)
        viewModel = ProductsViewModel(productsRepository, favoriteProductsRepository)

        viewModel.products.test {
            assertEquals(ProductsUiState.Loading, awaitItem())

            val success = awaitItem()
            assertTrue(success is ProductsUiState.Success)
            assertEquals(fakeProducts, (success as ProductsUiState.Success).products)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when repository emits error UiState is Error`() = runTest {
        every { productsRepository.getProducts() } returns flow {
            throw Exception("")
        }
        viewModel = ProductsViewModel(productsRepository, favoriteProductsRepository)

        viewModel.products.test {
            assertEquals(ProductsUiState.Loading, awaitItem())

            val error = awaitItem()
            assertTrue(error is ProductsUiState.Error)
            assertEquals(ProductsUiState.Error("").error, (error as ProductsUiState.Error).error)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `addToFavorites adds product to favorites if not found`() = runTest {
        val product = fakeProducts.first()

        every { productsRepository.getProducts() } returns flowOf(fakeProducts)
        coEvery { favoriteProductsRepository.getById(product.id) } returns null
        coEvery { favoriteProductsRepository.removeFromFavourite(product) } returns Unit
        coEvery { favoriteProductsRepository.addToFavourite(product) } returns Unit

        viewModel = ProductsViewModel(productsRepository, favoriteProductsRepository)
        viewModel.addProductToFavourites(product)

        coVerify(exactly = 1) { favoriteProductsRepository.addToFavourite(product) }
        coVerify(exactly = 0) { favoriteProductsRepository.removeFromFavourite(product) }

    }

    @Test
    fun `addToFavorites removes product from favorites if found`() = runTest {
        val product = fakeProducts.first()

        every { productsRepository.getProducts() } returns flowOf(fakeProducts)
        coEvery { favoriteProductsRepository.getById(product.id) } returns fakeProducts.first()
        coEvery { favoriteProductsRepository.removeFromFavourite(product) } returns Unit
        coEvery { favoriteProductsRepository.addToFavourite(product) } returns Unit

        viewModel = ProductsViewModel(productsRepository, favoriteProductsRepository)
        viewModel.addProductToFavourites(product)

        coVerify(exactly = 0) { favoriteProductsRepository.addToFavourite(product) }
        coVerify(exactly = 1) { favoriteProductsRepository.removeFromFavourite(product) }
    }

}