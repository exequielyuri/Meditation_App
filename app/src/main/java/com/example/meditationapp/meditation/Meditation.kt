package com.example.meditationapp.meditation

sealed class Meditation(
    val name: String,
    val instruction: String = "",
    val science: String = "",
    val spiritual: String = ""
) {
    object Trataka : Meditation(
        name = "Trataka"
    )

    object BeesBreath : Meditation(
        name = "Bee's Breath"
    )

    object Mindfulness : Meditation(
        name = "Mindfulness"
    )

    // maybe remove these meditations later
    object Posture : Meditation(
        name = "Posture"
    )

    object Ice : Meditation(
        name = "Ice"
    )

    object Custom : Meditation(
        name = "Custom"
    )
}
