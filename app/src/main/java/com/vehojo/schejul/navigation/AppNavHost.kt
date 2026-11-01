package com.vehojo.schejul.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vehojo.schejul.ui.screens.HomeScreen
import com.vehojo.schejul.ui.screens.NewLesson

@Composable
fun AppNavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Destination.HomeScreen.route,
    ) {
        composable(route = Destination.HomeScreen.route) {
            HomeScreen(onNextScreen = {
                navHostController.navigate(Destination.NewLesson.route) {launchSingleTop = true}
            })
        }
        composable(route = Destination.NewLesson.route) {
            NewLesson(onNextScreen = {
                //navHostController.navigate(Destination.HomeScreen.route)
                navHostController.popBackStack()
            })
        }
    }
}