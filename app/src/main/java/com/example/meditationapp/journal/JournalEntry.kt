package com.example.meditationapp.journal

class JournalEntry(
    val day: Int,
    val date: Int,
    val month: Int,
    val year: Int,
    val meditation: String, // change later to "meditation: Meditation"
    val durationMin: Int,
    val durationSec: Int,
    val content: String
    ) {
}