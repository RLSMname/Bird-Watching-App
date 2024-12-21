package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.birds.domain.model.Habitat
import com.example.myapplication.ui.components.BirdListView
import com.example.myapplication.ui.components.Header
import com.example.myapplication.ui.components.PlusButton
import com.example.myapplication.ui.viewmodels.BirdsViewState

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    state: BirdsViewState,
    onDeleteBird: (Int) -> Unit,
    onClickPlusButton: () -> Unit,
    onEditBird: (id:Int, name:String, order:String, family:String, habitat: Habitat, sightCount: Int) -> Unit
) {


    val headerText = "Your collection"
    val headerSubText = "ALWAYS READY FOR A NEW ADVENTURE"

    var enabled by remember {
        mutableStateOf(true)
    };

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Header(text = headerText, subText = headerSubText, modifier.weight(1f))
        Spacer(modifier = Modifier.height(30.dp))
        PlusButton(enabled = enabled) {
            enabled = false
            onClickPlusButton()
        }
        Spacer(modifier = Modifier.height(20.dp))
        BirdListView(modifier.weight(5f), state = state, onDeleteBird = onDeleteBird, onEditBird = onEditBird)
    }
}
