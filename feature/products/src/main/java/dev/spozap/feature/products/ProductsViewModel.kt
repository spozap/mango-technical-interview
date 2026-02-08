package dev.spozap.feature.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.spozap.core.data.model.Result
import dev.spozap.core.data.model.asResult
import dev.spozap.core.data.repository.ProductsRepository
import dev.spozap.core.model.Product
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
) : ViewModel() {

    val products = productsRepository.getProducts()
        .asResult()
        .map { result ->
            when (result) {
                is Result.Success -> ProductsUiState.Success(result.data)
                is Result.Error -> ProductsUiState.Error("")
                is Result.Loading -> ProductsUiState.Loading
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), ProductsUiState.Loading)

    fun addProductToFavourites(product: Product) {
        viewModelScope.launch {
            val favouriteProduct = productsRepository.getById(product.id)

            if (favouriteProduct == null) {
                productsRepository.addToFavourite(product)
            } else {
                productsRepository.removeFromFavourite(product)
            }
        }
    }

}