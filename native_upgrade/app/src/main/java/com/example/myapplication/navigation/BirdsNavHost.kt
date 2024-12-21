package com.example.myapplication.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.myapplication.birds.domain.model.Bird
import com.example.myapplication.ui.screens.AddEditScreen
import com.example.myapplication.ui.screens.MainScreen
import com.example.myapplication.ui.viewmodels.BirdsViewModel

@Composable
fun BirdsNavHost() {
    val navController = rememberNavController()
    val viewModel: BirdsViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = ListDisplayScreen) {
        composable<ListDisplayScreen> {

            val state by viewModel.state.collectAsStateWithLifecycle()
            MainScreen(
                state = state,
                onDeleteBird = { id -> viewModel.deleteBird(id) },
                onClickPlusButton = {
                    navController.navigate(AddScreen)
                }, onEditBird = { id, name, order, family, habitat, sightCount ->
                    navController.navigate(EditScreen(id, name, order, family, habitat, sightCount))
                })
        }
        composable<AddScreen> {
            AddEditScreen(
                text = "Add a new bird",
                subText = "GET A NEW FRIEND",
                buttonText = "ADD",
                onAddBird = { bird ->
                    viewModel.addBird(bird)
                    navController.popBackStack()
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable<EditScreen> { entry ->
            val args = entry.toRoute<EditScreen>()
            val id = args.id
            val name = args.name
            val order = args.order
            val family = args.family
            val habitat = args.habitat
            val sightCount = args.sightCount

            val text = if (id != null && id != -1) "Edit bird" else "Error retrieving bird :("
            AddEditScreen(text = text, subText = "", buttonText = "EDIT", onAddBird = { updatedBirdValues ->
                viewModel.updateBird(updatedBirdValues)
                navController.popBackStack()
            }, existingBird = Bird(id, name, order, family, habitat, sightCount), onBack = {
                navController.popBackStack()
            })
        }
//
    }
}
