package com.example.app.birds.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Bird(
    val id: Int,
    val name: String,
    val order: String,
    val family: String,
    val habitat: Habitat,
    val sightCount: Int
) {
}