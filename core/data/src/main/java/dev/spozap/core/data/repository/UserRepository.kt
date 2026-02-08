package dev.spozap.core.data.repository

import dev.spozap.core.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getProfile(): Flow<User>
}