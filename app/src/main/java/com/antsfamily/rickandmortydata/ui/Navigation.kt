package com.antsfamily.rickandmortydata.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.antsfamily.rickandmortydata.ui.characterinfo.CharacterInfoScreen
import com.antsfamily.rickandmortydata.ui.home.HomeScreen
import com.antsfamily.rickandmortydata.ui.splash.SplashScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = SplashScreen.ROUTE) {
        composable(SplashScreen.ROUTE) {
            SplashScreen.Content {
                navController.navigate(HomeScreen.ROUTE) {
                    popUpTo(SplashScreen.ROUTE) { inclusive = true }
                }
            }
        }
        composable(HomeScreen.ROUTE) {
            HomeScreen.Content(
                onCharacterClick = { id -> navController.navigate("character/$id") },
                onLocationClick = {},
                onEpisodeClick = {}
            )
        }
        composable(
            CharacterInfoScreen.ROUTE,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            CharacterInfoScreen.Content(id = it.arguments?.getInt("id") ?: 0)
        }
    }
}
