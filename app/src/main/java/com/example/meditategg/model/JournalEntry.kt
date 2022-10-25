package com.example.meditategg.model

data class JournalEntry(
    val id: String = "",
    val day: Int = -1,
    val date: Int = -1,
    val month: Int = -1,
    val year: Int = -1,
    val meditationName: String = "",
    val durationMin: Int = -1,
    val durationSec: Int = -1,
    val content: String = "",
)