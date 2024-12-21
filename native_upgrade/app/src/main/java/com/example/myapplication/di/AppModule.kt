package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.birds.domain.db.AppDatabase
import com.example.myapplication.birds.domain.db.BirdDao
import com.example.myapplication.birds.domain.model.Bird
import com.example.myapplication.birds.domain.model.Habitat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context):AppDatabase{
        return Room.databaseBuilder(context, AppDatabase::class.java, "bird_database")
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(appDatabase: AppDatabase):BirdDao{
        return appDatabase.birdDao();
    }
}