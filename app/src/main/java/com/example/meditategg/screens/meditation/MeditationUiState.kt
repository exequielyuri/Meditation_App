package com.example.meditategg.screens.meditation

data class MeditationUiState(
    val durationSec: Int = 0,
    val openDialog: Boolean = false,
    val userReflection: String = "",
    val meditating: Boolean = false,
)
