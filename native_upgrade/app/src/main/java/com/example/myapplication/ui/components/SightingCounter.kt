package com.example.myapplication.ui.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SightingCounter(
    modifier: Modifier = Modifier,  sightCount: Int,
    onValueChange: (Int) -> Unit
) {


    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomMediumText(
            text = "NUMBER OF TIMES YOU’VE SEEN THIS BIRD",
            size = 11.sp,
            lineHeight = 23.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            CounterButton(
                onClick = { if (sightCount > 1) onValueChange(sightCount - 1) }, text = "−", enabled = sightCount > 1
            )

            Text(
                text = sightCount.toString(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            CounterButton(
                onClick = { onValueChange(sightCount + 1) }, text = "+"
            )
        }
    }
}

@Composable
fun CounterButton(
    onClick: () -> Unit, text: String, modifier: Modifier = Modifier, enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) Color.White else Color.LightGray,
            contentColor = if (enabled) Color.Black else Color.Gray
        ),
        border = BorderStroke(1.dp, Color.Black),
        contentPadding = PaddingValues(0.dp),
        modifier = modifier.size(40.dp)
    ) {
        Text(
            text = text, fontSize = 24.sp, color = Color.Black, fontWeight = FontWeight.Bold
        )
    }
}
