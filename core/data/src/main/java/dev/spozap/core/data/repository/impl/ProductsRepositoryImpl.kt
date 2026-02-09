package dev.spozap.core.data.repository.impl

import dev.spozap.core.data.mappers.toModel
import dev.spozap.core.data.repository.ProductsRepository
import dev.spozap.core.database.dao.ProductDao
import dev.spozap.core.model.Product
import dev.spozap.core.network.api.ProductApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class ProductsRepositoryImpl @Inject constructor(
    private val productApi: ProductApi,
    private val productsDao: ProductDao
) : ProductsRepository {

    override fun getProducts(): Flow<List<Product>> {
        val apiProductsFlow = flow {
            val productsResponse = productApi.getProducts()
            emit(productsResponse.map { it.toModel() })
        }

        val favoritesFlow = productsDao.getFavourites()

        return combine(apiProductsFlow, favoritesFlow) { products, favorites ->
            val favoriteIds = favorites.map { it.id }.toSet()
            products.map { product ->
                product.copy(isFavourite = favoriteIds.contains(product.id))
            }
        }
    }
}