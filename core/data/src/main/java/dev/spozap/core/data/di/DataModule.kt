package dev.spozap.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.spozap.core.data.repository.UserRepository
import dev.spozap.core.data.repository.impl.UserRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {
    @Binds
    internal abstract fun bindsUserRepository(impl: UserRepositoryImpl): UserRepository
}