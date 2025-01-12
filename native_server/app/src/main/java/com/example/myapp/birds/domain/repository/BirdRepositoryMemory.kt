package com.example.myapp.birds.domain.repository

import arrow.core.Either
import arrow.core.right
import com.example.myapp.birds.domain.model.Bird
import com.example.myapp.util.Errors
import javax.inject.Inject

class BirdRepositoryMemory @Inject constructor(private val birds: MutableList<Bird>) :
    BirdRepository {
    private var nextId: Int = 10

    override suspend fun getBirds(): Either<Errors, List<Bird>> {
        return if (birds.isNotEmpty()) {
            Either.Right(birds)
        } else {
            Either.Left(Errors.NoBirds)
        }
    }

    override suspend fun getBirdById(id: String): Either<Errors, Bird> {
        val bird = birds.firstOrNull { it.id == id }
        if (bird == null)
            return Either.Left(Errors.NotFound)
        return bird.right()
    }

    override suspend fun addBird(bird: Bird, birdId: String?): Either<Errors, Bird> {
        if (birds.any { it.name == bird.name }) {
            return Either.Left(Errors.BirdAlreadyExists)
        }

        val newBird = bird.copy(id = nextId.toString())
        nextId++


        birds.add(newBird)
        return newBird.right()
    }

    override suspend fun deleteBird(id: String): Either<Errors, Unit> {
        val removed = birds.removeAll { it.id == id }
        return if (removed) {
            Either.Right(Unit)
        } else {
            Either.Left(Errors.NotFound)
        }
    }

    override suspend fun updateBird(bird: Bird): Either<Errors, Bird> {
        if (birds.any { it.name == bird.name && it.id != bird.id}) {
            return Either.Left(Errors.BirdAlreadyExists)
        }

        val index = birds.indexOfFirst { it.id == bird.id }
        return if (index != -1) {
            birds[index] = bird
            bird.right()
        } else {
            Either.Left(Errors.NotFound)
        }
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }

    override suspend fun insertAll(birds: List<Bird>) {
        TODO("Not yet implemented")
    }


}