package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.example.myapplication.birds.domain.model.Habitat

@Composable
fun SelectHabitat(
    selectedHabitat: Habitat,
    onValueChange: (Habitat) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val textState = remember { mutableStateOf(TextFieldValue(selectedHabitat.name)) }

    Box(modifier = modifier) {
        OutlinedTextField(
            value = textState.value,
            onValueChange = {
                textState.value = it
            },
            label = { Text("Select Habitat") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = androidx.compose.material.icons.Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown"
                    )
                }
            },
            modifier = modifier
                .fillMaxWidth()

        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart)
        ) {
            Habitat.entries.forEach { habitat ->
                DropdownMenuItem(
                    text = { Text(habitat.name) },
                    onClick = {
                        textState.value = TextFieldValue(habitat.name)
                        onValueChange(habitat)
                        expanded = false
                    }
                )
            }
        }
    }
}
