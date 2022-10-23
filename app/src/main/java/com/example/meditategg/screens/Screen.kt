package com.example.meditategg.screens

sealed class Screen(val route: String) {
    object MeditationMap : Screen("meditation_map")
    object Journal : Screen("journal")
    object Settings : Screen("settings")
    object MeditationScreen : Screen("meditation_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
