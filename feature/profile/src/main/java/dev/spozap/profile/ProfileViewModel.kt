package dev.spozap.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.spozap.core.data.repository.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    userRepository: UserRepository
) : ViewModel() {

    val profileUiState: StateFlow<ProfileUIState> = userRepository.getProfile()
        .map { ProfileUIState.Success(it) }
        .catch {
            ProfileUIState.Error("")
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), ProfileUIState.Loading)

}