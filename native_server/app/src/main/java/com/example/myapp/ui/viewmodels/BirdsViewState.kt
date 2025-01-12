package com.example.myapp.ui.viewmodels

import com.example.myapp.birds.domain.model.Bird

data class BirdsViewState(
    val isLoading: Boolean = false,
    val birds: List<Bird> = emptyList(),
    val error: String? = null,
    val connectedToServer: Boolean = true
)