package com.example.app.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.app.ui.theme.DarkOrange
import com.example.app.ui.theme.MediumOrange
import com.example.app.ui.theme.White

@Composable
fun PlusButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MediumOrange,
    contentDescription: String? = "Add",
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        enabled = enabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(50),
        modifier = modifier.size(height = 48.dp, width = 140.dp)
    ) {
        Icon(
            imageVector = Icons.Default.AddCircle,
            contentDescription = contentDescription,
            tint = White,
            modifier = Modifier.fillMaxSize()

        )
    }
}
