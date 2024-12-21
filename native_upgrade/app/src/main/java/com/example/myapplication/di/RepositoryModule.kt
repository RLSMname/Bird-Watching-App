package com.example.myapplication.di

import com.example.myapplication.birds.domain.repository.BirdRepository
import com.example.myapplication.birds.domain.repository.BirdRepositoryDb
import com.example.myapplication.birds.domain.repository.BirdRepositoryMemory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

//    @Binds
//    @Singleton
//    abstract fun provideBirdsRepository(impl: BirdRepositoryMemory) : BirdRepository
    @Binds
    @Singleton
    abstract fun provideBirdsRepository(impl: BirdRepositoryDb) : BirdRepository
}