package com.example.meditategg.model

import com.google.firebase.Timestamp

data class JournalEntry(
    val timestamp: Timestamp? = null,
    val dayOfWeek: Int = -1,
    val day: Int = -1,
    val month: Int = -1,
    val year: Int = -1,
    val meditationName: String = "",
    val durationMin: Int = -1,
    val durationSec: Int = -1,
    val content: String = "",
    val bookmark: Boolean = false
)