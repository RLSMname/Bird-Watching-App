package com.example.app.ui.viewmodels

import com.example.app.birds.domain.model.Bird

data class BirdsViewState(
    val isLoading: Boolean = false,
    val birds: List<Bird> = emptyList(),
    val error: String? = null
)