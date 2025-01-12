package com.example.myapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.birds.domain.model.Bird
import com.example.myapp.birds.domain.repository.BirdRepository
import com.example.myapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class BirdsViewModel @Inject constructor(
    @Named("Db") private val birdRepositoryDb: BirdRepository,
    @Named("Network") private val birdRepositoryNetwork: BirdRepository
) : ViewModel() {
    private val _state = MutableStateFlow(BirdsViewState())
    val state = _state.asStateFlow()

    init {
        Log.d("TEST", "BirdsViewModel initialized")
        getBirds()
    }

    private fun getBirds() {
        Log.d("TEST", "TRIGGERED GET BIRDS")
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            birdRepositoryNetwork.getBirds()
                .onRight { birds ->

                    try {
                        Log.d("LOG", "BEFORE")

                        Log.d("LOG", "REPO STATE:$birdRepositoryDb")

                        // Clear local DB and insert new birds (both are suspend functions)
                        birdRepositoryDb.deleteAll() // This will suspend until complete
                        Log.d("LOG", "DELETE ALL SUCCESS")
                        birdRepositoryDb.insertAll(birds) // This will also suspend until complete
                        Log.d("LOG", "INSERT ALL SUCCESS")
                        Log.d("LOG", "AFTER")
                    } catch (e: Exception) {
                        Log.d("LOG", "Error updating local DB: ${e.message}")
                        sendEvent(Event.Toast("Error updating local database"))
                        return@onRight // Early return if DB operations fail
                    }

                    _state.update {
                        it.copy(birds = birds.toMutableList())
                    }

                    Log.d("TEST", "BIRDS FROM GETBIRDS ${_state.value.birds}")
                }.onLeft { error ->

                    Log.d("LOG", "Error obtaining birds from server $error")
                    sendEvent(Event.Toast(error.text))

                    birdRepositoryDb.getBirds()
                        .onRight { birds ->
                            _state.update {
                                it.copy(birds = birds.toMutableList(), connectedToServer = false)
                            }
                            Log.d("TEST", "BIRDS FROM GETBIRDS ${_state.value.birds}")
                        }.onLeft { error ->

                            _state.update {
                                it.copy(
                                    error = error.name,
                                    connectedToServer = false
                                )
                            }
                            Log.d("LOG", "Error obtaining birds $error")
                            sendEvent(Event.Toast(error.text))
                        }

                }
            _state.update { it.copy(isLoading = false) }
        }
    }

    fun getBirdById(id: String): Bird? {
        var bird: Bird? = null;
        viewModelScope.launch {

            if(_state.value.connectedToServer){
                birdRepositoryNetwork.getBirdById(id).onRight { birdWithId ->
                    Log.d("DBTEST", "VIEW MODEL BIRD WITH ID: $birdWithId")
                    bird = birdWithId
                }.onLeft {
                    Log.d("TEST", "No bird with this id")
                }
            }
            else {
                birdRepositoryDb.getBirdById(id).onRight { birdWithId ->
                    Log.d("DBTEST", "VIEW MODEL BIRD WITH ID: $birdWithId")
                    bird = birdWithId
                }.onLeft {
                    Log.d("TEST", "No bird with this id")
                }
            }

        }
        Log.d("DBTEST", "VIEW MODEL BIRD BEFORE RETURN: $bird")
        return bird
    }


    fun updateBird(updatedBirdValues: Bird) {
        Log.d("TEST", "BIRD RECEIVED FROM UI $updatedBirdValues")
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            if(_state.value.connectedToServer){
                birdRepositoryNetwork.updateBird(updatedBirdValues).onRight { updatedBird ->
                    val updatedBirds = _state.value.birds.map { bird ->
                        if (bird.id == updatedBird.id) updatedBird else bird
                    }
                    _state.update { it.copy(birds = updatedBirds) }
                }.onLeft { error ->
                    _state.update { it.copy(error = error.name) }
                    Log.d("LOG", "Error updating bird: $error")
                    sendEvent(Event.Toast(error.text))
                }
            }
            birdRepositoryDb.updateBird(updatedBirdValues).onRight { updatedBird ->
                val updatedBirds = _state.value.birds.map { bird ->
                    if (bird.id == updatedBird.id) updatedBird else bird
                }

                if(!_state.value.connectedToServer){
                    _state.update { it.copy(birds = updatedBirds) }
                }
            }.onLeft { error ->
                _state.update { it.copy(error = error.name) }
                Log.d("LOG", "Error updating bird: $error")
                sendEvent(Event.Toast(error.text))
            }
            _state.update { it.copy(isLoading = false) }
        }
    }

    fun addBird(newBird: Bird) {
        Log.d("TEST", "BIRD RECEIVED FROM UI $newBird")
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }


            if(_state.value.connectedToServer){
                birdRepositoryNetwork.addBird(newBird).onRight { addedBird ->

                    Log.d("TEST", "BIRD ADDED TO REPO, LIST STATE INITIALLY: ${_state.value.birds}")

                    val updatedBirds = (_state.value.birds + addedBird)

                    //add in db
                    birdRepositoryDb.addBird(newBird, addedBird.id).onRight { addedBird ->

                        Log.d("TEST", "BIRD ADDED TO REPO, LIST STATE INITIALLY: ${_state.value.birds}")

                        Log.d("TEST", "BIRD ADDED TO REPO, LIST STATE AFTERWARDS: ${_state.value.birds}")
                    }.onLeft { error ->
                        _state.update { it.copy(error = error.name) }
                        Log.d("LOG", "Error adding bird: $error")
                        sendEvent(Event.Toast(error.text))
                    }

                    _state.update { it.copy(birds = updatedBirds) }

                    Log.d("TEST", "BIRD ADDED TO REPO, LIST STATE AFTERWARDS: ${_state.value.birds}")
                }.onLeft { error ->
                    _state.update { it.copy(error = error.name) }
                    Log.d("LOG", "Error adding bird: $error")
                    sendEvent(Event.Toast(error.text))

                }
            }
            else {
                birdRepositoryDb.addBird(newBird).onRight { addedBird ->

                    Log.d("TEST", "BIRD ADDED TO REPO, LIST STATE INITIALLY: ${_state.value.birds}")

                    val updatedBirds = (_state.value.birds + addedBird)
                    if(!_state.value.connectedToServer) {
                        _state.update { it.copy(birds = updatedBirds) }
                    }

                    Log.d("TEST", "BIRD ADDED TO REPO, LIST STATE AFTERWARDS: ${_state.value.birds}")
                }.onLeft { error ->
                    _state.update { it.copy(error = error.name) }
                    Log.d("LOG", "Error adding bird: $error")
                    sendEvent(Event.Toast(error.text))

                }
            }

            _state.update { it.copy(isLoading = false) }
        }
    }


    fun deleteBird(id: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            if(_state.value.connectedToServer){
                birdRepositoryNetwork.deleteBird(id).onRight {
                    //success
                    val updatedBirds = _state.value.birds.filter { it.id != id }
                    _state.update {
                        it.copy(birds = updatedBirds)
                    }
                    Log.d("Test", "Bird with ID $id deleted successfully.")
                }.onLeft { error ->
                    _state.update {
                        it.copy(error = error.name)
                    }
                    Log.d("LOG", "Error deleting bird with ID $id: $error")
                    sendEvent(Event.Toast(error.text))
                }
            }
            birdRepositoryDb.deleteBird(id).onRight {
                //success
                val updatedBirds = _state.value.birds.filter { it.id != id }

                if(!_state.value.connectedToServer){
                    _state.update {
                        it.copy(birds = updatedBirds)
                    }
                }

                Log.d("Test", "Bird with ID $id deleted successfully.")
            }.onLeft { error ->
                _state.update {
                    it.copy(error = error.name)
                }
                Log.d("LOG", "Error deleting bird with ID $id: $error")
                sendEvent(Event.Toast(error.text))
            }
            _state.update { it.copy(isLoading = false) }
        }
    }
}