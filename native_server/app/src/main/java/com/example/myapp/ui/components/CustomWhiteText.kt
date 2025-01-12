package com.example.myapp.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CustomWhiteText(text: String, size: TextUnit = 14.sp) {
    Text(
        text = text,
        style = TextStyle(
            color = Color.White,
            fontWeight = FontWeight.Black,
            fontSize = size
        )
    )
}