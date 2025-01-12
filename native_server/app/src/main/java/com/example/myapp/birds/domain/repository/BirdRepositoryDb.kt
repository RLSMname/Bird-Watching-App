package com.example.myapp.birds.domain.repository

import android.util.Log
import arrow.core.Either
import com.example.myapp.birds.domain.db.BirdDao
import com.example.myapp.birds.domain.model.Bird
import com.example.myapp.util.Errors
import java.util.UUID
import javax.inject.Inject

class BirdRepositoryDb @Inject constructor(private val birdDao: BirdDao) : BirdRepository {
    override suspend fun getBirds(): Either<Errors, List<Bird>> {
        return try {
            val birds = birdDao.getAll()
            if (birds.isNotEmpty()) {
                Log.d("TDB", "GET ALL BIRDS=$birds")
                Either.Right(birds)
            } else {
                Either.Left(Errors.NoBirds)
            }
        } catch (exception: Exception) {
            Either.Left(Errors.DatabaseError)  // Handle any database-related errors
        }
    }

    override suspend fun getBirdById(id: String): Either<Errors, Bird> {
        return try {
            val bird = birdDao.getById(id)
            Log.d("TDB", "GETID ID $id FOR BIRD $bird")
            Either.Right(bird)
        } catch (exception: Exception) {
            Log.d("TDB", "GETID EXCEPTION ${exception.message}")
            Either.Left(Errors.NotFound)
        }
    }

    override suspend fun addBird(bird: Bird, birdId: String?): Either<Errors, Bird> {
        return try {
            //val existingBird = birdDao.getAll().find { it.name == bird.name }
            val existingBirds = birdDao.getAllWithName(bird.name);

            if (existingBirds.isNotEmpty()) {
                Log.d("TDB", "ADD EXCEPTION: Bird already exists")
                return Either.Left(Errors.BirdAlreadyExists)
            }

            val newBirdId = birdId ?: UUID.randomUUID().toString()
            val rowId = birdDao.insert(bird.copy(id = newBirdId))

            val newBird = bird.copy(id = rowId.toString())
            Log.d("TDB", "ADDED BIRD WITH ID ${newBird.id}")
            Either.Right(newBird)

        } catch (exception: Exception) {
            Log.d("TDB", "ADD EXCEPTION:" + exception.message)
            Either.Left(Errors.DatabaseError)  // Handle any database-related errors
        }
    }

    override suspend fun deleteBird(id: String): Either<Errors, Unit> {
        return try {
            val bird = birdDao.getById(id)
            birdDao.delete(id)  // Delete the bird from the database
            Log.d("TDB", "DELETED BIRD WITH ID ${id}")
            Either.Right(Unit)
        } catch (exception: Exception) {
            Log.d("TDB", "DELETE EXCEPTION: NOT FOUND ${id}")
            Either.Left(Errors.NotFound)  // Bird not found in database
        }
    }

    override suspend fun updateBird(bird: Bird): Either<Errors, Bird> {
        return try {
            val existingBird = birdDao.getById(bird.id)  // Check if the bird exists
            birdDao.update(bird)
            Log.d("TDB", "UPDATED BIRD WITH ID ${bird.id}")// Update the bird in the database
            Either.Right(bird)
        } catch (exception: Exception) {
            Log.d("TDB", "UPDATE EXCEPTION:" + exception.message)
            Either.Left(Errors.NotFound)  // Bird not found in database
        }
    }

    override suspend fun deleteAll() {
        Log.d("LOG", "Before deleteAll DAO")
        birdDao.deleteAll()
        Log.d("LOG", "After deleteAll DAO")
    }

    override suspend fun insertAll(birds: List<Bird>) {
        Log.d("LOG", "Before insertAll DAO")
        birdDao.insertAll(birds)
        Log.d("LOG", "After insertALL DAO")
    }
}