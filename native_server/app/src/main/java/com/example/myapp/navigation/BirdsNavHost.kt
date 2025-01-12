package com.example.myapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.myapp.birds.domain.model.Bird
import com.example.myapp.ui.screens.AddEditScreen
import com.example.myapp.ui.screens.MainScreen
import com.example.myapp.ui.viewmodels.BirdsViewModel

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

            val text = if (id != null && id != "") "Edit bird" else "Error retrieving bird :("
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
