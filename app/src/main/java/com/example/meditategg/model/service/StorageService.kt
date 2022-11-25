package com.example.meditategg.model.service

import com.example.meditategg.model.JournalEntry
import com.example.meditategg.model.User

interface StorageService {
    fun addJournalListener(
        userId: String,
        onDocumentEvent: (String, JournalEntry) -> Unit,
        onError: (Throwable) -> Unit
    )
    fun removeJournalListener()
    fun saveEntry(
        userId: String,
        entry: JournalEntry,
        onResult: (Throwable?) -> Unit
    )
    fun updateEntry(
        userId: String,
        entry: JournalEntry,
        onResult: (Throwable?) -> Unit)
    fun getEntry(
        userId: String,
        entryId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (JournalEntry) -> Unit
    )
    fun deleteEntry(
        userId: String,
        entryId: String,
        onResult: (Throwable?) -> Unit
    )
    fun deleteAllUserEntries()
    fun getFavoriteMeditations(
        userId: String,
        onError: (Throwable?) -> Unit,
        onSuccess: (List<String>) -> Unit
    )
    fun getUser(
        userId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (User?) -> Unit
    )
    fun addUser(
        user: User,
        onResult: (Throwable?) -> Unit
    )
}