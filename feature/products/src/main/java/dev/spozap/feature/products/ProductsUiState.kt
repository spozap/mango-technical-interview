package dev.spozap.feature.products

import dev.spozap.core.model.Product

sealed class ProductsUiState {
    object Loading : ProductsUiState()
    class Success(val products: List<Product>) : ProductsUiState()
    class Error(val error: String) : ProductsUiState()
}