package com.example.app.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.app.ui.screens.AddEditScreen
import com.example.app.ui.screens.MainScreen
import com.example.app.ui.viewmodels.BirdsViewModel

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
                }, onEditBird = { id ->
                    navController.navigate(EditScreen(id))
                })
        }
        composable<AddScreen> {
            AddEditScreen(
                text = "Add a new bird",
                subText = "GET A NEW FRIEND",
                buttonText = "ADD",
                onAddBird = { bird ->
                    navController.popBackStack()
                    viewModel.addBird(bird)
                }
            )
        }
        composable<EditScreen> { entry ->
            val args = entry.toRoute<EditScreen>()
            val id = args.id
            val bird = viewModel.getBirdById(id)
            val text = if (bird != null) "Edit bird ${bird.name}" else "Error retrieving bird :("
            AddEditScreen(text = text, subText = "", buttonText = "EDIT", onAddBird = { updatedBirdValues ->
                viewModel.updateBird(updatedBirdValues)
                navController.popBackStack()
            }, existingBird = bird)
        }
    }
}
