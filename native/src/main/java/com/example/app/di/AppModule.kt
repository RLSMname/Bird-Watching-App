package com.example.app.di

import com.example.app.birds.domain.model.Bird
import com.example.app.birds.domain.model.Habitat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideMutableList():MutableList<Bird> {
        return mutableListOf(Bird(1, "Name1", "Order1", "Family1", Habitat.FOREST, 1), Bird(2, "Name2", "Order2", "Family2", Habitat.WETLAND, 2))
    }
}