package com.example.myapplication.ui.viewmodels

import com.example.myapplication.birds.domain.model.Habitat

data class BirdFormState(
    var name: String = "",
    var order: String = "",
    var family: String = "",
    var habitat: Habitat = Habitat.FOREST,
    var sightCount: Int = 0
)