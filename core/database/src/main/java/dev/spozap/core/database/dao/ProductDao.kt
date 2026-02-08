package dev.spozap.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.spozap.core.database.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: ProductEntity)

    @Query("SELECT * FROM favourite_products WHERE id = :id")
    suspend fun getById(id: Int): ProductEntity?

    @Query("SELECT * FROM favourite_products")
    fun getFavourites(): Flow<List<ProductEntity>>

    @Delete
    suspend fun delete(product: ProductEntity)

}