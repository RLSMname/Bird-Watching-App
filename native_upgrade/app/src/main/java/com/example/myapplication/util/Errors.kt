package com.example.myapplication.util

enum class Errors(val text: String) {
    NoBirds("No birds found"),
    NotFound("There is no bird with this id"),
    BirdAlreadyExists("There already exists a bird with this name or id"),

    DatabaseError("There is a problem with the database")
}