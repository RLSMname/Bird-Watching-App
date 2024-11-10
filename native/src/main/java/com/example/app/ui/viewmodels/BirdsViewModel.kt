package com.example.app.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.birds.domain.model.Bird
import com.example.app.birds.domain.repository.BirdRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BirdsViewModel @Inject constructor(private val birdRepository: BirdRepository) : ViewModel() {
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
            birdRepository.getBirds()
                .onRight { birds ->
                    _state.update {
                        it.copy(birds = birds.toMutableList())
                    }

                    Log.d("TEST", "BIRDS FROM GETBIRDS ${_state.value.birds}")
                }.onLeft { error ->
                    _state.update {
                        it.copy(
                            error = error.name
                        )
                    }
                    Log.d("Test", "Error obtaining birds $error")
                    sendEvent(Event.Toast(error.error.message))
                }
            _state.update { it.copy(isLoading = false) }
        }
    }

    fun getBirdById(id: Int): Bird? {
        var bird: Bird? = null;
        viewModelScope.launch {
            birdRepository.getBirdById(id).onRight { birdWithId ->
                bird = birdWithId
            }.onLeft {
                Log.d("TEST", "No bird with this id")
            }
        }
        return bird
    }

    fun updateBird(updatedBirdValues: Bird) {
        Log.d("TEST", "BIRD RECEIVED FROM UI $updatedBirdValues")
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            birdRepository.updateBird(updatedBirdValues).onRight { updatedBird ->
                val updatedBirds = _state.value.birds.map { bird ->
                    if (bird.id == updatedBird.id) updatedBird else bird
                }
                _state.update { it.copy(birds = updatedBirds) }
            }.onLeft { error ->
                _state.update { it.copy(error = error.name) }
                Log.d("TEST", "Error updating bird: $error")
                sendEvent(Event.Toast(error.text))
            }
            _state.update { it.copy(isLoading = false) }
        }
    }

    fun addBird(newBird: Bird) {
        Log.d("TEST", "BIRD RECEIVED FROM UI $newBird")
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            birdRepository.addBird(newBird).onRight { addedBird ->

                Log.d("TEST", "BIRD ADDED TO REPO, LIST STATE INITIALLY: ${_state.value.birds}")

                val updatedBirds = (_state.value.birds + addedBird)
                _state.update { it.copy(birds = updatedBirds) }


                Log.d("TEST", "BIRD ADDED TO REPO, LIST STATE AFTERWARDS: ${_state.value.birds}")
            }.onLeft { error ->
                _state.update { it.copy(error = error.name) }
                Log.d("TEST", "Error adding bird: $error")
                sendEvent(Event.Toast(error.text))
            }
            _state.update { it.copy(isLoading = false) }
        }
    }


    fun deleteBird(id: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            birdRepository.deleteBird(id).onRight {
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
                Log.d("Test", "Error deleting bird with ID $id: $error")
                sendEvent(Event.Toast(error.text))
            }

            _state.update { it.copy(isLoading = false) }
        }
    }
}