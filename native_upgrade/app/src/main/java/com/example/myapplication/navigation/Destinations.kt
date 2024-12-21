package com.example.myapplication.navigation

import com.example.myapplication.birds.domain.model.Habitat
import kotlinx.serialization.Serializable

@Serializable
object ListDisplayScreen

@Serializable
object AddScreen

@Serializable
data class EditScreen(val id: Int, val name:String, val order:String, val family:String, val habitat: Habitat, val sightCount:Int)