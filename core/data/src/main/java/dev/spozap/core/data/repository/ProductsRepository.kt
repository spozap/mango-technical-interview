package dev.spozap.core.data.repository

import dev.spozap.core.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun getProducts(): Flow<List<Product>>
    suspend fun getById(productId: Int): Product?

}