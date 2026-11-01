package com.vehojo.schejul.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
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
                navHostController.safeNavigate(Destination.NewLesson.route) {launchSingleTop = true}
            })
        }
        composable(
            route = Destination.NewLesson.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(500)
                )
            }
        ) {
            NewLesson(onNextScreen = {
                if (navHostController.previousBackStackEntry != null) {
                    navHostController.safePopBackStack()
                }
            })
        }
    }
}