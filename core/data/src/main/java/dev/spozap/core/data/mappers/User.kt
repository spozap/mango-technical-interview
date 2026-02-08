package dev.spozap.core.data.mappers

import dev.spozap.core.model.User
import dev.spozap.core.network.dto.responses.UserProfileResponse

/**
 * Mapper function to transform a [UserProfileResponse] into a [User].
 *
 * Produces a [User] instance`.
 */
fun UserProfileResponse.toModel() = User(
    id = id,
    username = username,
    email = email,
    phone = phone
)