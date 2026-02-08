package dev.spozap.core.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.spozap.core.database.ApplicationDatabase
import dev.spozap.core.database.DB_NAME
import dev.spozap.core.database.dao.ProductDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DatabaseModule {

    @[Provides Singleton]
    fun providesDatabase(
        @ApplicationContext context: Context
    ): ApplicationDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = ApplicationDatabase::class.java,
            name = DB_NAME
        ).build()
    }

    @[Provides Singleton]
    fun providesUserDao(database: ApplicationDatabase): ProductDao = database.productDao()

}