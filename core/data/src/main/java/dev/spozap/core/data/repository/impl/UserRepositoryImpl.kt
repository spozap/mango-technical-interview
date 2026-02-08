package dev.spozap.core.data.repository.impl

import android.util.Log
import dev.spozap.core.data.mappers.toModel
import dev.spozap.core.data.repository.UserRepository
import dev.spozap.core.model.User
import dev.spozap.core.network.api.ProfileApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val profileApi: ProfileApi
) : UserRepository {
    override fun getProfile(): Flow<User> = flow {
        try {
            Log.d("Network", "Entra getProfile")
            val response = profileApi.getUserProfile()
            emit(response.toModel())
        } catch (e: Error) {
            Log.d("ERROR", e.printStackTrace().toString())
        }
    }.catch {
        Log.d("Network", it.localizedMessage)
    }
}