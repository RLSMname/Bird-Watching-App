package com.example.app.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.app.birds.domain.model.Bird
import com.example.app.ui.viewmodels.BirdsViewState

@Composable
fun BirdListView(
    modifier: Modifier = Modifier,
    state: BirdsViewState,
    onDeleteBird: (Int) -> Unit,
    onEditBird: (Int) -> Unit
) {
    LoadingDialog(isLoading = state.isLoading)

    var showDialog by remember { mutableStateOf(false) }
    var birdToDelete by remember { mutableStateOf<Bird?>(null) }

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            items(state.birds) { bird ->
                BirdCard(
                    name = bird.name,
                    order = bird.order,
                    family = bird.family,
                    habitatTemp = bird.habitat.name,
                    number = bird.sightCount,
                    onEditClick = { onEditBird(bird.id) },
                    onDeleteClick = {
                        Log.d("Test", "Delete clicked for ${bird.name}")
                        birdToDelete = bird
                        showDialog = true
                    })
            }
        }

        DeleteBirdDialog(
            showDialog = showDialog,
            birdToDelete = birdToDelete,
            onDeleteConfirmed = { id ->
                onDeleteBird(id)
                showDialog = false
            },
            onDismiss = {
                showDialog = false
            }
        )
    }


}