package com.vehojo.schejul.navigation

sealed class Destination(val route: String) {
    data object HomeScreen: Destination(ROUTE_HOME)
    data object NewLesson: Destination(ROUTE_NEWLES)

    companion object {
        private const val ROUTE_HOME = "route_home"
        private const val ROUTE_NEWLES = "route_newles"
    }
}