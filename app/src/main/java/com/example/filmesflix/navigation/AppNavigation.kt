package com.example.filmesflix.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.filmesflix.data.MockData
import com.example.filmesflix.ui.theme.DetailScreen
import com.example.filmesflix.ui.theme.HomeScreen
import com.example.filmesflix.ui.theme.IntroScreen
import com.example.filmesflix.ui.theme.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(Screen.Splash.route) {
            SplashScreen(onTimeout = {
                navController.navigate(Screen.Intro.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            })
        }

        composable(Screen.Intro.route) {
            IntroScreen(onFinishIntro = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Intro.route) { inclusive = true }
                }
            })
        }

        composable(Screen.Home.route) {
            HomeScreen(onItemClick = { itemId ->
                navController.navigate(Screen.Detail.createRoute(itemId))
            })
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId") ?: return@composable
            val item = MockData.listaItens.find { it.id == itemId }

            item?.let {
                DetailScreen(
                    item = it,
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }
    }
}