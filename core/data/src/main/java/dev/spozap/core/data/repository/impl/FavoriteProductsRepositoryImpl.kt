package dev.spozap.core.data.repository.impl

import dev.spozap.core.data.mappers.toEntity
import dev.spozap.core.data.mappers.toModel
import dev.spozap.core.data.repository.FavoriteProductsRepository
import dev.spozap.core.database.dao.ProductDao
import dev.spozap.core.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteProductsRepositoryImpl @Inject constructor(
    private val productDao: ProductDao
) : FavoriteProductsRepository {

    override val favoriteProducts: Flow<List<Product>> = productDao.getFavourites()
        .map { products -> products.map { product -> product.toModel() } }

    override suspend fun addToFavourite(product: Product) {
        productDao.insert(product.toEntity())
    }

    override suspend fun removeFromFavourite(product: Product) {
        productDao.delete(product.toEntity())
    }

    override fun getFavoritesCount(): Flow<Int> {
        return productDao.getFavoritesCount()
    }

}