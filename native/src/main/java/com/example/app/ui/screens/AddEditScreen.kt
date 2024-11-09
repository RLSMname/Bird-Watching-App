package com.example.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.birds.domain.model.Bird
import com.example.app.birds.domain.model.Habitat
import com.example.app.ui.components.CustomForm
import com.example.app.ui.components.CustomMediumText
import com.example.app.ui.components.FooterButton
import com.example.app.ui.components.Header
import com.example.app.ui.viewmodels.BirdFormState
import com.example.app.util.BirdValidator
import java.util.Locale

@Composable
fun AddEditScreen(
    text: String,
    subText: String,
    buttonText: String,
    modifier: Modifier = Modifier,
    onAddBird: (Bird) -> Unit,
    existingBird: Bird? = null
) {
    var birdFormState by remember {
        mutableStateOf(
            BirdFormState(
                name = existingBird?.name ?: "",
                order = existingBird?.order ?: "",
                family = existingBird?.family ?: "",
                habitat = existingBird?.habitat ?: Habitat.FOREST,
                sightCount = existingBird?.sightCount ?: 1
            )
        )
    }

    var validationErrors by remember { mutableStateOf<List<String>>(emptyList()) }

    var enabled by remember { mutableStateOf<Boolean>(true) }

    val validator = BirdValidator()


    Column(modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Header(text = text, subText = subText, modifier.weight(1f))
        CustomForm(
            modifier.weight(4f),
            birdFormState = birdFormState,
            onValueChange = { updatedState ->
                birdFormState = updatedState
            })


        if (validationErrors.isNotEmpty()) {

            validationErrors.forEach { error ->
                CustomMediumText(text = error, color = Color.Red, size = 14.sp)
            }
            Spacer(modifier = Modifier.height(10.dp))

        }


        FooterButton(text = buttonText, modifier = modifier.weight(1f), enabled = enabled) {

            val newBird = Bird(
                id = existingBird?.id ?: -1,
                name = birdFormState.name.uppercase(Locale.getDefault()),
                order = birdFormState.order.uppercase(Locale.getDefault()),
                family = birdFormState.family.uppercase(Locale.getDefault()),
                habitat = birdFormState.habitat,
                sightCount = birdFormState.sightCount
            )

            validationErrors = validator.validate(newBird)

            if (validationErrors.isEmpty()) {
                enabled = false
                onAddBird(newBird)

            }

        }
    }
}