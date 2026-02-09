package dev.spozap.core.data.repository

import app.cash.turbine.test
import dev.spozap.core.data.mappers.toEntity
import dev.spozap.core.data.repository.impl.ProductsRepositoryImpl
import dev.spozap.core.database.dao.ProductDao
import dev.spozap.core.model.Product
import dev.spozap.core.model.ProductRating
import dev.spozap.core.network.api.ProductApi
import dev.spozap.core.network.dto.responses.ProductRatingResponse
import dev.spozap.core.network.dto.responses.ProductResponse
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
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

private val favoriteProducts = listOf(
    fakeProducts[0]
)

class ProductsRepositoryImplTest {

    private val productApi: ProductApi = mockk()
    private val productDao: ProductDao = mockk()
    private lateinit var repository: ProductsRepository

    @Before
    fun setup() {
        repository = ProductsRepositoryImpl(productApi, productDao)
    }

    @Test
    fun `getProducts should combine API and favorites`() = runTest {
        coEvery { productApi.getProducts() } returns fakeProducts.map { it.toResponse() }
        every { productDao.getFavourites() } returns flow { emit(favoriteProducts.map { it.toEntity() }) }

        repository.getProducts().test {
            val result = awaitItem()
            assertEquals(true, result.first().isFavourite)
            assertEquals(false, result[1].isFavourite)
            cancelAndConsumeRemainingEvents()
        }
    }


}

private fun Product.toResponse() = ProductResponse(
    id = id,
    title = title,
    price = price,
    description = description,
    category = category,
    image = image,
    rating = ProductRatingResponse(
        rate = rating.rate,
        count = rating.count
    )
)