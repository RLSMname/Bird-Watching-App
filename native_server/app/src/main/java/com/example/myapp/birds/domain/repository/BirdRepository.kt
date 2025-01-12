package com.example.myapp.birds.domain.repository

import arrow.core.Either
import com.example.myapp.birds.domain.model.Bird
import com.example.myapp.util.Errors


interface BirdRepository {
    suspend fun getBirds(): Either<Errors, List<Bird>>
    suspend fun getBirdById(id: String): Either<Errors, Bird>

    suspend fun addBird(bird: Bird, birdId: String? = null): Either<Errors, Bird>
    suspend fun deleteBird(id: String): Either<Errors, Unit>
    suspend fun updateBird(bird: Bird): Either<Errors, Bird>

    suspend fun deleteAll()
    suspend fun insertAll(birds: List<Bird>)
}