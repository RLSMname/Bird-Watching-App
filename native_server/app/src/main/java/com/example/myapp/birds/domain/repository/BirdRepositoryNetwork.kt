package com.example.myapp.birds.domain.repository

import android.util.Log
import arrow.core.Either
import com.example.myapp.birds.domain.model.Bird
import com.example.myapp.service.RetrofitService
import com.example.myapp.util.Errors
import javax.inject.Inject

class BirdRepositoryNetwork @Inject constructor(private val birdService: RetrofitService) :
    BirdRepository {


    override suspend fun getBirds(): Either<Errors, List<Bird>> {
        return Either.catch {
            Log.d("NetworkRepository", "GET BIRDS")
            birdService.getAllBirds()
        }.mapLeft { exception ->
            Log.d("NetworkRepository", "GET BIRDS EXCEPTION:${exception.message}")
            Errors.NetworkError
        }
    }

    override suspend fun getBirdById(id: String): Either<Errors, Bird> {
        return Either.catch {
            Log.d("NetworkRepository", "GET BIRD BY ID")
            birdService.getBirdById(id)
        }.mapLeft { exception ->
            Log.d("NetworkRepository", "GET BIRD BY ID EXCEPTION:${exception.message}")
            Errors.NetworkError
        }
    }

    override suspend fun addBird(bird: Bird, birdId: String?): Either<Errors, Bird> {
        return Either.catch {
            Log.d("NetworkRepository", "ADD BIRD")
            birdService.addBird(bird, "sesh")
        }.mapLeft { exception ->
            Log.d("NetworkRepository", "ADD BIRD EXCEPTION:${exception.message}")
            Errors.NetworkError
        }
    }

    override suspend fun deleteBird(id: String): Either<Errors, Unit> {
        return Either.catch {
            Log.d("NetworkRepository", "DELETE BIRD WITH ID $id")
            birdService.deleteBird(id, "sesh")
        }.mapLeft { exception ->
            Log.d("NetworkRepository", "DELETE BIRD EXCEPTION:${exception.message}")
            Errors.NetworkError
        }
    }

    override suspend fun updateBird(bird: Bird): Either<Errors, Bird> {
        return Either.catch {
            Log.d("NetworkRepository", "UPDATE BIRD WITH ID ${bird.id}")
            birdService.updateBird(bird, "sesh")
        }.mapLeft { exception ->
            Log.d("NetworkRepository", "UPDATE BIRD EXCEPTION:${exception.message}")
            Errors.NetworkError
        }
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }

    override suspend fun insertAll(birds: List<Bird>) {
        TODO("Not yet implemented")
    }
}