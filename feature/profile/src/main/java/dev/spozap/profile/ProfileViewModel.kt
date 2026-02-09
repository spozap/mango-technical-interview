package dev.spozap.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.spozap.core.data.model.Result
import dev.spozap.core.data.model.asResult
import dev.spozap.core.data.repository.ProductsRepository
import dev.spozap.core.data.repository.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    userRepository: UserRepository,
    productsRepository: ProductsRepository
) : ViewModel() {

    val profileUiState: StateFlow<ProfileUIState> = userRepository.getProfile()
        .asResult()
        .map { result ->
            when (result) {
                is Result.Success -> ProfileUIState.Success(result.data)
                is Result.Loading -> ProfileUIState.Loading
                is Result.Error -> ProfileUIState.Error("")
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), ProfileUIState.Loading)

    val favoriteProductsCount = productsRepository.getFavoritesCount()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0)

}