package com.example.myapplication.birds.domain.repository

import android.util.Log
import arrow.core.Either
import com.example.myapplication.birds.domain.db.BirdDao
import com.example.myapplication.birds.domain.model.Bird
import com.example.myapplication.util.Errors
import javax.inject.Inject

class BirdRepositoryDb @Inject constructor(private val birdDao: BirdDao) : BirdRepository {
    override suspend fun getBirds(): Either<Errors, List<Bird>> {
        return try {
            val birds = birdDao.getAll()
            if (birds.isNotEmpty()) {
                Log.d("DBTEST", "GET ALL BIRDS=$birds")
                Either.Right(birds)
            } else {
                Either.Left(Errors.NoBirds)
            }
        } catch (exception: Exception) {
            Either.Left(Errors.DatabaseError)  // Handle any database-related errors
        }
    }

    override suspend fun getBirdById(id: Int): Either<Errors, Bird> {
        return try {
            val bird = birdDao.getById(id)
            Log.d("DBTEST", "GETID ID $id FOR BIRD $bird")
            Either.Right(bird)
        } catch (exception: Exception) {
            Log.d("DBTEST", "GETID EXCEPTION ${exception.message}")
            Either.Left(Errors.NotFound)
        }
    }

    override suspend fun addBird(bird: Bird): Either<Errors, Bird> {
        return try {
            //val existingBird = birdDao.getAll().find { it.name == bird.name }
            val existingBirds = birdDao.getAllWithName(bird.name);

            if (existingBirds.isNotEmpty()) {

                return Either.Left(Errors.BirdAlreadyExists)
            }

            val rowId = birdDao.insert(bird.copy(id = 0))

            val newBird = bird.copy(id = rowId.toInt())

            Either.Right(newBird)
        } catch (exception: Exception) {
            Log.d("DBTEST", "ADD EXCEPTION:" + exception.message)
            Either.Left(Errors.DatabaseError)  // Handle any database-related errors
        }
    }

    override suspend fun deleteBird(id: Int): Either<Errors, Unit> {
        return try {
            val bird = birdDao.getById(id)
            birdDao.delete(id)  // Delete the bird from the database
            Either.Right(Unit)
        } catch (exception: Exception) {
            Either.Left(Errors.NotFound)  // Bird not found in database
        }
    }

    override suspend fun updateBird(bird: Bird): Either<Errors, Bird> {
        return try {
            val existingBird = birdDao.getById(bird.id)  // Check if the bird exists
            birdDao.update(bird)  // Update the bird in the database
            Either.Right(bird)
        } catch (exception: Exception) {
            Either.Left(Errors.NotFound)  // Bird not found in database
        }
    }
}