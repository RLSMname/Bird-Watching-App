package com.example.myapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MediumOrange

@Composable
fun Header(text: String, subText: String, modifier: Modifier = Modifier) {
    val myColor = MediumOrange
    Column(
        modifier
            .fillMaxWidth()
            .background(myColor)
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        CustomWhiteText(text = text, size = 40.sp)
        Spacer(modifier = Modifier.height(10.dp))
        CustomWhiteText(text = subText)
    }
}