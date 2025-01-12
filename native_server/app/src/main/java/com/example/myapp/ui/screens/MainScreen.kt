package com.example.myapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapp.birds.domain.model.Habitat
import com.example.myapp.ui.components.BirdListView
import com.example.myapp.ui.components.Header
import com.example.myapp.ui.components.PlusButton
import com.example.myapp.ui.viewmodels.BirdsViewState

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    state: BirdsViewState,
    onDeleteBird: (String) -> Unit,
    onClickPlusButton: () -> Unit,
    onEditBird: (id:String, name:String, order:String, family:String, habitat: Habitat, sightCount: Int) -> Unit
) {


    val headerText = "Your collection"
    val headerSubText = "ALWAYS READY FOR A NEW ADVENTURE"

    var enabled by remember {
        mutableStateOf(true)
    };

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Header(text = headerText, subText = headerSubText, modifier.weight(1f))
        Spacer(modifier = Modifier.height(4.dp))
        ConnectionStatusIcon(isConnected = state.connectedToServer)
        Spacer(modifier = Modifier.height(10.dp))
        PlusButton(enabled = enabled) {
            enabled = false
            onClickPlusButton()
        }
        Spacer(modifier = Modifier.height(20.dp))
        BirdListView(modifier.weight(5f), state = state, onDeleteBird = onDeleteBird, onEditBird = onEditBird)
    }
}


@Composable
fun ConnectionStatusIcon(isConnected: Boolean) {
    Icon(
        imageVector = if (isConnected) Icons.Default.LocationOn else Icons.Default.Close,
        contentDescription = if (isConnected) "Connected to the Internet" else "No Internet Connection",
        modifier = Modifier
            .padding(vertical = 10.dp)
            .size(24.dp),
    )
}