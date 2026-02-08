package dev.spozap.core.model

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: ProductRating
)

data class ProductRating(
    val rate: Double,
    val count: Int,
)