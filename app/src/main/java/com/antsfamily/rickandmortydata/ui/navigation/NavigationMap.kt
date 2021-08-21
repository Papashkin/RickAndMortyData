package com.antsfamily.rickandmortydata.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.antsfamily.rickandmortydata.presentation.MainViewModel
import com.antsfamily.rickandmortydata.ui.characterinfo.CharacterInfoScreen
import com.antsfamily.rickandmortydata.ui.main.MainScreen

@Composable
fun SetupNavigation(viewModel: ViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(
                viewModel as MainViewModel,
                onItemClick = { navController.navigate("characterInfo") }
            )
        }
        composable("characterInfo") {
            CharacterInfoScreen()
        }
    }
}
