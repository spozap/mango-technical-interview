package dev.spozap.core.network.dto.responses

import kotlinx.serialization.Serializable

@Serializable
data class UserProfileResponse(
    val id: String,
    val username: String,
    val email: String,
    val phone: String
)