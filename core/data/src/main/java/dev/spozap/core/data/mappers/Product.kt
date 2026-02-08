package dev.spozap.core.data.mappers

import dev.spozap.core.database.entity.ProductEntity
import dev.spozap.core.database.entity.ProductRatingEntity
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

/**
 * Mapper function to transform a [Product] into a [ProductEntity].
 *
 * Produces a [ProductEntity] instance`.
 */

fun Product.toEntity() = ProductEntity(
    id = id,
    title = title,
    price = price,
    description = description,
    image = image,
    category = category,
    rating = rating.toEntity()
)

/**
 * Mapper function to transform a [ProductRating] into a [ProductRatingEntity].
 *
 * Produces a [ProductRatingEntity] instance`.
 */

fun ProductRating.toEntity() = ProductRatingEntity(
    rate = rate,
    count = count
)

/**
 * Mapper function to transform a [ProductEntity] into a [Product].
 *
 * Produces a [Product] instance`.
 */

fun ProductEntity.toModel() = Product(
    id = id,
    title = title,
    price = price,
    description = description,
    image = image,
    category = category,
    rating = rating.toModel()
)

/**
 * Mapper function to transform a [ProductRatingEntity] into a [ProductRating].
 *
 * Produces a [ProductRating] instance`.
 */

fun ProductRatingEntity.toModel() = ProductRating(
    rate = rate,
    count = count
)