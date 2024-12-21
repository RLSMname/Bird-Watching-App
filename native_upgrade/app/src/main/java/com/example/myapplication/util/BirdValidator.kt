package com.example.myapplication.util

import com.example.myapplication.birds.domain.model.Bird

class BirdValidator {
    private val maxOrderLength = 16 // Phaethontiformes
    private val maxFamilyLength = 13 // Coraciiformes or Dromaiidae


    fun validate(bird: Bird): List<String> {
        val errors = mutableListOf<String>()

        if (bird.name.isEmpty()) {
            errors.add("Name cannot be empty.")
        } else if (!bird.name.matches("^[a-zA-Z\\s]+$".toRegex())) {
            errors.add("Name must only contain letters and spaces.")
        }

        if (bird.order.isEmpty()) {
            errors.add("Order cannot be empty.")
        } else if (!bird.order.matches("^[a-zA-Z]+$".toRegex())) {
            errors.add("Order must only contain letters, no spaces.")
        } else if (bird.order.length > maxOrderLength) {
            errors.add("Order must not exceed $maxOrderLength letters.")
        }

        if (bird.family.isEmpty()) {
            errors.add("Family cannot be empty.")
        } else if (!bird.family.matches("^[a-zA-Z]+$".toRegex())) {
            errors.add("Family must only contain letters, no spaces.")
        } else if (bird.family.length > maxFamilyLength) {
            errors.add("Family must not exceed $maxFamilyLength letters.")
        }

        return errors
    }
}

