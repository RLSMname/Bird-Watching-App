package com.example.myapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.DarkGreen
import com.example.myapplication.ui.theme.DarkOrange
import com.example.myapplication.ui.theme.LightGreen


@Composable
fun BirdCard(
    name: String,
    order: String,
    family: String,
    habitatTemp: String,
    number: Int,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val myColor = DarkGreen
    val myEditColor = LightGreen
    val myDeleteColor = DarkOrange

    var enabledButtons by remember {
        mutableStateOf(true)
    }

    Column(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(myColor)
            .padding(start = 15.dp, end = 15.dp, top = 20.dp, bottom = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CustomWhiteText(text = name, size = 24.sp)
        Row {
            CustomWhiteText(text = order)
            Spacer(modifier = Modifier.width(5.dp))
            CustomWhiteText(text = family)
        }
        CustomWhiteText(text = habitatTemp)
        CustomWhiteText(text = "Seen $number time(s)", size = 12.sp)
        Row {
            CustomButton(
                text = "EDIT",
                backGroundColor = myEditColor,
                modifier.weight(1f),
                enabled = enabledButtons
            ) {
                enabledButtons = false
                onEditClick()
            }
            Spacer(modifier = Modifier.width(5.dp))
            CustomButton(
                text = "DELETE",
                backGroundColor = myDeleteColor,
                modifier.weight(1f),
                enabled = enabledButtons
            ) {
                onDeleteClick()
            }
        }
    }
}