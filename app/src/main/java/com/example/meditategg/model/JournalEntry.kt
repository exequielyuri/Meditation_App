package com.example.meditategg.model

data class JournalEntry(
    val id: String = "",
    val day: Int,
    val date: Int,
    val month: Int,
    val year: Int,
    val meditationName: String,
    val durationMin: Int,
    val durationSec: Int,
    val content: String = "",
)