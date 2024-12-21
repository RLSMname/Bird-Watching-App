package com.example.app.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.app.birds.domain.model.Bird

@Composable
fun DeleteBirdDialog(
    showDialog: Boolean,
    birdToDelete: Bird?,
    onDeleteConfirmed: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    if (showDialog && birdToDelete != null) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Confirm Deletion") },
            text = { Text("Are you sure you want to delete ${birdToDelete.name}?") },
            confirmButton = {
                Button(
                    onClick = {
                        onDeleteConfirmed(birdToDelete.id)
                        onDismiss()
                    }
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                Button(
                    onClick = onDismiss
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}
