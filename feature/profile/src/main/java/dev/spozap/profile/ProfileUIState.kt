package dev.spozap.profile

import dev.spozap.core.model.User

sealed interface ProfileUIState {
    object Loading : ProfileUIState
    data class Success(val profile: User) : ProfileUIState
    data class Error(val message: String) : ProfileUIState
}