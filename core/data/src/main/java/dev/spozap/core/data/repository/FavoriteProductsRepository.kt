package dev.spozap.core.data.repository

import dev.spozap.core.model.Product
import kotlinx.coroutines.flow.Flow

interface FavoriteProductsRepository {
    val favoriteProducts: Flow<List<Product>>

    suspend fun addToFavourite(product: Product)
    suspend fun removeFromFavourite(product: Product)
    fun getFavoritesCount(): Flow<Int>
}