package com.antsfamily.rickandmortydata.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.antsfamily.rickandmortydata.ui.characterinfo.CharacterInfoScreen
import com.antsfamily.rickandmortydata.ui.home.HomeScreen
import com.antsfamily.rickandmortydata.ui.splash.SplashScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen.Content {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            }
        }
        composable(Screen.Home.route) {
            HomeScreen.Content(
                onCharacterClick = { id -> navController.navigate("character/$id") },
                onLocationClick = {},
                onEpisodeClick = {}
            )
        }
        composable(
            Screen.CharacterInfo.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            CharacterInfoScreen.Content(id = it.arguments?.getInt("id") ?: 0)
        }
    }
}

sealed class Screen(val route: String) {
    object Splash: Screen("splash")
    object Home: Screen("home")
    object CharacterInfo: Screen("character/{id}")
}
