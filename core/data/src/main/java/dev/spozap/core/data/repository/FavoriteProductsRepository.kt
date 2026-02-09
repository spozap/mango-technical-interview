package dev.spozap.core.data.repository

import dev.spozap.core.model.Product
import kotlinx.coroutines.flow.Flow

interface FavoriteProductsRepository {
    val favoriteProducts: Flow<List<Product>>
}