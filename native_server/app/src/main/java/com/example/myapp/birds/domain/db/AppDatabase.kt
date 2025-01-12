package com.example.myapp.birds.domain.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapp.birds.domain.model.Bird

@Database(entities = [Bird::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun birdDao(): BirdDao

    //this is for singleton pattern that is thread safe
    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "bird_db_server")
                    .build()
                    .also { Instance = it }
            }

        }

    }

}