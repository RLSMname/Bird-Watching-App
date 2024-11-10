package com.example.app.util

enum class Errors(val text: String) {
    NoBirds("No birds found"),
    NotFound("There is no bird with this id"),
    BirdAlreadyExists("There already exists a bird with this name or id"),

//    InvalidName("Name can only contain letters"),
//    InvalidFamily("Family can only contain letters"),
//    InvalidOrder("Order can only contain letters")
}