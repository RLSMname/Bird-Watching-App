package com.example.app.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CustomMediumText(
    text: String,
    size: TextUnit = 11.sp,
    lineHeight: TextUnit = 23.sp,
    color: Color = Color.Black
) {
    Text(
        text = text,
        style = TextStyle(
            color = color,
            fontWeight = FontWeight.Medium,
            fontSize = size,
            lineHeight = lineHeight,
        )
    )
}