package com.example.myapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.myapp.ui.theme.LightGreen

@Composable
fun FooterButton(text: String, modifier: Modifier = Modifier,enabled: Boolean = true, onClick: () -> Unit) {
    val myColor =if(enabled) LightGreen else Color.LightGray
    Column(
        modifier
            .fillMaxSize()
            .background(myColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            enabled = enabled,
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            CustomWhiteText(text = text, size = 40.sp)
        }
    }
}