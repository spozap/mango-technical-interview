package dev.spozap.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.spozap.core.database.dao.ProductDao
import dev.spozap.core.database.entity.ProductEntity

private const val DB_VERSION: Int = 1
const val DB_NAME: String = "app_db"

@Database(version = DB_VERSION, entities = [ProductEntity::class], exportSchema = true)
internal abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}