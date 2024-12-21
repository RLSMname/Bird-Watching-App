package com.example.myapplication.birds.domain.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.birds.domain.model.Bird

@Dao
interface BirdDao {
    @Insert
    suspend fun insert(bird: Bird):Long

    @Update
    suspend fun update(bird: Bird)

    @Query("SELECT * FROM birds")
    suspend fun getAll(): List<Bird>

    @Query("SELECT * FROM birds WHERE id=:id")
    suspend fun getById(id: Int): Bird

    @Query("DELETE FROM birds WHERE id=:id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM birds WHERE name = :name")
    suspend fun getAllWithName(name: String):List<Bird>
}