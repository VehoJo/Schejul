package com.vehojo.schejul.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vehojo.schejul.Lesson
import com.vehojo.schejul.LessonViewModel
import com.vehojo.schejul.ui.screens.AddLesson
import com.vehojo.schejul.ui.screens.HomeScreen
import com.vehojo.schejul.ui.screens.NewLesson
import kotlin.collections.mutableListOf

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    lessonViewModel: LessonViewModel
) {
    NavHost(
        navController = navHostController,
        startDestination = Destination.HomeScreen.route,
    ) {
        composable(route = Destination.HomeScreen.route) {
            val lessons by lessonViewModel.lessons.collectAsState()
            HomeScreen(onNextScreen = {
                navHostController.safeNavigate(Destination.NewLesson.route) {
                    launchSingleTop = true
                }
            },
                lessons = lessons,
                onDeleteLesson = { lessons ->
                    lessonViewModel.deleteLesson(lessons)
                }
            )
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
            },
                onSaveLesson = { lesson ->
                    lessonViewModel.addLesson(lesson)
                    navHostController.safePopBackStack()
                }
            )
        }
    }
}