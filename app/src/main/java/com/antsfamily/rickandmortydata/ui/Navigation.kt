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
    NavHost(navController = navController, startDestination = Route.Splash) {
        composable(Route.Splash) {
            SplashScreen.Content {
                navController.navigate(Route.Home) {
                    popUpTo(Route.Splash) { inclusive = true }
                }
            }
        }
        composable(Route.Home) {
            HomeScreen.Content(
                onLocationClick = {},
                onEpisodeClick = {},
                navController = navController
            )
        }
        composable(
            Route.CharacterInfo,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            CharacterInfoScreen.Content(
                id = it.arguments?.getInt("id") ?: 0,
                navController = navController
            )
        }
    }
}

object Route {
    const val Splash = "splash"
    const val Home = "home"
    const val CharacterInfo = "character/{id}"
}
