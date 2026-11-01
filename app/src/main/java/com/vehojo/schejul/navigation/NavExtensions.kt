package com.vehojo.schejul.navigation

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder

private fun NavHostController.isResumed() =
    currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED

fun NavHostController.safeNavigate(route: String, builder: NavOptionsBuilder.() -> Unit = {}) {
    if (isResumed()) navigate(route, builder)
}

fun NavHostController.safePopBackStack() {
    if (isResumed()) popBackStack()
}

