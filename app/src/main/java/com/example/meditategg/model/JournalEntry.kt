package com.example.meditategg.model

data class JournalEntry(
    val id: String = "",
    val day: Int,
    val date: Int,
    val month: Int,
    val year: Int,
    val meditation: String, // change later to "meditation: Meditation"
    val durationMin: Int,
    val durationSec: Int,
    val content: String = "",
)