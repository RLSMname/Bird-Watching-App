package com.example.myapp.birds.domain.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapp.birds.domain.model.Bird

@Dao
interface BirdDao {
    @Insert
    suspend fun insert(bird: Bird):Long

    @Update
    suspend fun update(bird: Bird)

    @Query("SELECT * FROM birds")
    suspend fun getAll(): List<Bird>

    @Query("SELECT * FROM birds WHERE id=:id")
    suspend fun getById(id: String): Bird

    @Query("DELETE FROM birds WHERE id=:id")
    suspend fun delete(id: String)

    @Query("SELECT * FROM birds WHERE name = :name")
    suspend fun getAllWithName(name: String):List<Bird>

    @Query("DELETE FROM birds")
    suspend fun deleteAll()

    @Insert
    suspend fun insertAll(birds: List<Bird>): List<Long>
}