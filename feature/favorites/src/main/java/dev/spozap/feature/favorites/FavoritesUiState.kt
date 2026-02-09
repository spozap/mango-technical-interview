package dev.spozap.feature.favorites

import dev.spozap.core.model.Product

sealed class FavoritesUiState {
    data class Success(val products: List<Product>) : FavoritesUiState()
    data class Error(val error: String) : FavoritesUiState()
    data object Loading : FavoritesUiState()
    data object Empty : FavoritesUiState()
}