package com.example.myapplication.birds.domain.repository

import arrow.core.Either
import com.example.myapplication.birds.domain.model.Bird
import com.example.myapplication.util.Errors


interface BirdRepository {
    suspend fun getBirds(): Either<Errors, List<Bird>>
    suspend fun getBirdById(id: Int): Either<Errors, Bird>

    suspend fun addBird(bird: Bird): Either<Errors, Bird>
    suspend fun deleteBird(id: Int): Either<Errors, Unit>
    suspend fun updateBird(bird: Bird): Either<Errors, Bird>
}