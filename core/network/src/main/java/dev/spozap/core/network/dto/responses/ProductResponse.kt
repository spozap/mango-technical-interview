package dev.spozap.core.network.dto.responses

import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: ProductRatingResponse
)

@Serializable
data class ProductRatingResponse(
    val rate: Double,
    val count: Int,
)