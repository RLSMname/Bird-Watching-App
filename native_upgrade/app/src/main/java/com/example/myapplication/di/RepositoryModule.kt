package com.example.app.di

import com.example.app.birds.domain.repository.BirdRepository
import com.example.app.birds.domain.repository.BirdRepositoryMemory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideBirdsRepository(impl: BirdRepositoryMemory) : BirdRepository
}