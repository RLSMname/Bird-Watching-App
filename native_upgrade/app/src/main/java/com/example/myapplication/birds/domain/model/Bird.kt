package com.example.myapplication.birds.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "birds")
data class Bird(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val order: String,
    val family: String,
    val habitat: Habitat,
    val sightCount: Int
) {
}