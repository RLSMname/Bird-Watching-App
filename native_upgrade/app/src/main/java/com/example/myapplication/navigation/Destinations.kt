package com.example.app.navigation

import kotlinx.serialization.Serializable

@Serializable
object ListDisplayScreen

@Serializable
object AddScreen

@Serializable
data class EditScreen(val id: Int)