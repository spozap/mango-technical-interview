package dev.spozap.core.network.api

import dev.spozap.core.network.dto.responses.UserProfileResponse
import retrofit2.http.GET

interface ProfileApi {
    @GET("users/8")
    suspend fun getUserProfile(): UserProfileResponse
}