package com.example.myapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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
import com.example.myapp.birds.domain.model.Bird
import com.example.myapp.birds.domain.model.Habitat
import com.example.myapp.ui.components.CustomForm
import com.example.myapp.ui.components.CustomMediumText
import com.example.myapp.ui.components.FooterButton
import com.example.myapp.ui.components.Header
import com.example.myapp.ui.viewmodels.BirdFormState
import com.example.myapp.util.BirdValidator
import java.util.Locale

@Composable
fun AddEditScreen(
    text: String,
    subText: String,
    buttonText: String,
    modifier: Modifier = Modifier,
    onAddBird: (Bird) -> Unit,
    existingBird: Bird? = null,
    onBack : ()->Unit
) {

    Log.d("DBTEST", "EXISTING BIRD=$existingBird")

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

    Log.d("DBTEST", "BIRDFORMSTATE IN ADDEDITBIRD: $birdFormState;")

    var validationErrors by remember { mutableStateOf<List<String>>(emptyList()) }

    var enabled by remember { mutableStateOf<Boolean>(true) }

    val validator = BirdValidator()


    Column(modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Header(text = text, subText = subText, modifier.weight(1f))
        Button(onClick = onBack ) { Text("GO BACK") }
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
                id = existingBird?.id ?: "",
                name = birdFormState.name.uppercase(Locale.getDefault()),
                order = birdFormState.order.uppercase(Locale.getDefault()),
                family = birdFormState.family.uppercase(Locale.getDefault()),
                habitat = birdFormState.habitat,
                sightCount = birdFormState.sightCount
            )

            validationErrors = validator.validate(newBird)

            if (validationErrors.isEmpty()) {
                enabled = false
                try{
                    onAddBird(newBird)
                }
               catch (exception: Exception){
                   enabled = true
               }

            }

        }
    }
}