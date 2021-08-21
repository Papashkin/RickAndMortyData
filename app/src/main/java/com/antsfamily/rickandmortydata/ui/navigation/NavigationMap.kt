package com.antsfamily.rickandmortydata.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.antsfamily.rickandmortydata.ui.characterinfo.CharacterInfoScreen
import com.antsfamily.rickandmortydata.ui.main.MainScreen

@Composable
fun SetupNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen { id -> navController.navigate("character/$id") }
        }
        composable(
            "character/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            CharacterInfoScreen(id = it.arguments?.getInt("id") ?: 0)
        }
    }
}
