package com.example.meditationapp

sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard")
    object Journal : Screen("journal")
    object Settings : Screen("settings")
}
