package dev.spozap.core.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_products")
data class ProductEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val image: String,
    val category: String,
    @Embedded
    val rating: ProductRatingEntity
)

data class ProductRatingEntity(
    val rate: Double,
    val count: Int
)