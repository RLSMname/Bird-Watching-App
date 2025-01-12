package com.example.myapp.birds.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
@Entity(tableName = "birds")
data class Bird(
    @PrimaryKey()
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val order: String,
    val family: String,
    val habitat: Habitat,
    val sightCount: Int
) {
}