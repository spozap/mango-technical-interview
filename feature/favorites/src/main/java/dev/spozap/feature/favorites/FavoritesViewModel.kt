package dev.spozap.feature.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.spozap.core.data.model.Result
import dev.spozap.core.data.model.asResult
import dev.spozap.core.data.repository.FavoriteProductsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesProductsRepository: FavoriteProductsRepository
) : ViewModel() {

    val products = favoritesProductsRepository.favoriteProducts
        .asResult()
        .map { result ->
            when (result) {
                is Result.Success -> {
                    if (result.data.isEmpty()) {
                        FavoritesUiState.Empty
                    } else {
                        FavoritesUiState.Success(result.data)
                    }
                }

                is Result.Error -> {
                    FavoritesUiState.Error("Ha ocurrido un error al cargar los productos favoritos")
                }

                Result.Loading -> {
                    FavoritesUiState.Loading
                }

            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), FavoritesUiState.Loading)

}