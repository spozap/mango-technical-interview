package dev.spozap.core.data.repository

import app.cash.turbine.test
import dev.spozap.core.data.mappers.toEntity
import dev.spozap.core.data.repository.impl.FavoriteProductsRepositoryImpl
import dev.spozap.core.database.dao.ProductDao
import dev.spozap.core.model.Product
import dev.spozap.core.model.ProductRating
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
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

class FavoriteProductsRepositoryImplTest {

    private val productDao: ProductDao = mockk()
    private lateinit var repository: FavoriteProductsRepositoryImpl

    @Before
    fun setup() {
        every { productDao.getFavourites() } returns flowOf(emptyList())
        repository = FavoriteProductsRepositoryImpl(productDao)

    }
    
    @Test
    fun `addToFavourite should call insert`() = runTest {

        coEvery { productDao.insert(any()) } just Runs

        repository.addToFavourite(fakeProducts[0])

        coVerify { productDao.insert(fakeProducts[0].toEntity()) }
    }

    @Test
    fun `removeFromFavourite should call delete`() = runTest {
        coEvery { productDao.delete(any()) } just Runs

        repository.removeFromFavourite(fakeProducts[0])

        coVerify { productDao.delete(fakeProducts[0].toEntity()) }
    }

    @Test
    fun `getFavoritesCount should return correct count`() = runTest {
        every { productDao.getFavoritesCount() } returns flowOf(5)

        repository.getFavoritesCount().test {
            val count = awaitItem()
            assertEquals(5, count)
            cancelAndConsumeRemainingEvents()
        }
    }

}