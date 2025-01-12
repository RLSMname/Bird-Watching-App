package com.example.myapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomFormField(
    placeholder: String, labelText: String, value: String, onValueChange: (String) -> Unit
) {

    val myColor = Color.Black


    Column(verticalArrangement = Arrangement.spacedBy(5.dp), modifier = Modifier.fillMaxWidth()) {
        CustomMediumText(text = labelText)
        TextField(

            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                CustomMediumText(
                    text = placeholder, size = 12.sp, color = Color.Gray
                )
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = myColor,
                unfocusedTextColor = myColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )

        )
    }
}