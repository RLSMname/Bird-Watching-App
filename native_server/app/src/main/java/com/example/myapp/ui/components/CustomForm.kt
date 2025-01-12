package com.example.myapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapp.ui.viewmodels.BirdFormState

@Composable
fun CustomForm(
    modifier: Modifier = Modifier,
    birdFormState: BirdFormState,
    onValueChange: (BirdFormState) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 25.dp, start = 25.dp, end = 25.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomFormField(placeholder = "Insert common name...",
            labelText = "COMMON NAME",
            value = birdFormState.name,
            onValueChange = { newName ->
                onValueChange(birdFormState.copy(name = newName))
            })
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            Column(Modifier.weight(1f)) {
                CustomFormField(placeholder = "Insert order...",
                    labelText = "ORDER",
                    value = birdFormState.order,
                    onValueChange = { newOrder ->
                        onValueChange(birdFormState.copy(order = newOrder))
                    })
            }
            Column(Modifier.weight(1f)) {
                CustomFormField(placeholder = "Insert family...",
                    labelText = "FAMILY",
                    value = birdFormState.family,
                    onValueChange = { newFamily ->
                        onValueChange(birdFormState.copy(family = newFamily))
                    })
            }


        }
        SelectHabitat(selectedHabitat = birdFormState.habitat,
            onValueChange = { newHabitat ->
                onValueChange(birdFormState.copy(habitat = newHabitat))
            })

        SightingCounter(modifier,
            sightCount = birdFormState.sightCount,
            onValueChange = { newSightCount ->
                onValueChange(birdFormState.copy(sightCount = newSightCount))
            })
    }
}