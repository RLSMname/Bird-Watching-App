package com.example.myapplication.ui.viewmodels

import com.example.myapplication.birds.domain.model.Bird

data class BirdsViewState(
    val isLoading: Boolean = false,
    val birds: List<Bird> = emptyList(),
    val error: String? = null
)