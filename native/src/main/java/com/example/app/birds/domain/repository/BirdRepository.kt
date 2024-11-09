package com.example.app.birds.domain.repository

import arrow.core.Either
import com.example.app.birds.domain.model.Bird
import com.example.app.util.Errors


interface BirdRepository {
    suspend fun getBirds(): Either<Errors, List<Bird>>
    suspend fun getBirdById(id: Int): Either<Errors, Bird>

    suspend fun addBird(bird: Bird): Either<Errors, Bird>
    suspend fun deleteBird(id: Int): Either<Errors, Unit>
    suspend fun updateBird(bird: Bird): Either<Errors, Bird>
}