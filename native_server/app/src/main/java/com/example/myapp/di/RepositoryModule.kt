package com.example.myapp.di

import com.example.myapp.birds.domain.repository.BirdRepository
import com.example.myapp.birds.domain.repository.BirdRepositoryDb
import com.example.myapp.birds.domain.repository.BirdRepositoryNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

//    @Binds
//    @Singleton
//    abstract fun provideBirdsRepository(impl: BirdRepositoryMemory) : BirdRepository
    @Binds
    @Singleton
    @Named("Db")
    abstract fun provideBirdsRepositoryDb(impl: BirdRepositoryDb) : BirdRepository

    @Binds
    @Singleton
    @Named("Network")
    abstract fun provideBirdsRepositoryNetwork(impl: BirdRepositoryNetwork) : BirdRepository

}