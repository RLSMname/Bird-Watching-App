package com.example.myapp.navigation

import com.example.myapp.birds.domain.model.Habitat
import kotlinx.serialization.Serializable

@Serializable
object ListDisplayScreen

@Serializable
object AddScreen

@Serializable
data class EditScreen(val id: String, val name:String, val order:String, val family:String, val habitat: Habitat, val sightCount:Int)