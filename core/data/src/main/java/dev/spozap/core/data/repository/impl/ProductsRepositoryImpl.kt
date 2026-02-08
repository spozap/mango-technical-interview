package dev.spozap.core.data.repository.impl

import dev.spozap.core.data.mappers.toModel
import dev.spozap.core.data.repository.ProductsRepository
import dev.spozap.core.model.Product
import dev.spozap.core.network.api.ProductApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class ProductsRepositoryImpl @Inject constructor(
    private val productApi: ProductApi
) : ProductsRepository {
    override fun getProducts(): Flow<List<Product>> = flow {
        val productsResponse = productApi.getProducts()
        emit(productsResponse.map { it.toModel() })
    }
}