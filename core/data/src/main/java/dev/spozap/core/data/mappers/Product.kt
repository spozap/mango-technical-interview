package dev.spozap.core.data.mappers

import dev.spozap.core.model.Product
import dev.spozap.core.model.ProductRating
import dev.spozap.core.network.dto.responses.ProductRatingResponse
import dev.spozap.core.network.dto.responses.ProductResponse

/**
 * Mapper function to transform a [ProductResponse] into a [Product].
 *
 * Produces a [Product] instance`.
 */
fun ProductResponse.toModel() = Product(
    id = id,
    title = title,
    price = price,
    description = description,
    category = category,
    image = image,
    rating = rating.toModel()
)

/**
 * Mapper function to transform a [ProductRatingResponse] into a [ProductRating].
 *
 * Produces a [ProductRating] instance`.
 */
fun ProductRatingResponse.toModel() = ProductRating(
    rate = rate,
    count = count
)