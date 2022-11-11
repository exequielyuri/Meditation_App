package com.example.meditategg.model.service.impl

import com.example.meditategg.model.JournalEntry
import com.example.meditategg.model.service.StorageService
import com.google.firebase.firestore.DocumentChange.Type.*
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class StorageServiceImpl @Inject constructor() : StorageService {
    private var listenerRegistration: ListenerRegistration? = null

    override fun addJournalListener(
        userId: String,
        onDocumentEvent: (String, JournalEntry) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val query = Firebase.firestore
            .collection(USER_COLLECTION)
            .document(userId)
            .collection(ENTRY_COLLECTION)
            .orderBy("timestamp", Query.Direction.DESCENDING)

        listenerRegistration = query.addSnapshotListener { value, error ->
            if (error != null) {
                onError(error)
                return@addSnapshotListener
            }

            value?.documentChanges?.forEach {
                val action = when(it.type) {
                    ADDED -> ADD
                    REMOVED -> REMOVE
                    else -> MODIFY
                }
                val entry = it.document.toObject<JournalEntry>()
                onDocumentEvent(action, entry)
            }
        }
    }

    override fun removeJournalListener() {
        listenerRegistration?.remove()
    }

    override fun saveEntry(
        userId: String,
        entry: JournalEntry,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore
            .collection(USER_COLLECTION)
            .document(userId)
            .collection(ENTRY_COLLECTION)
            .whereEqualTo("day", entry.day)
            .whereEqualTo("month", entry.month)
            .whereEqualTo("year", entry.year)
            .get()
            .addOnSuccessListener {
                if (it.documents.isEmpty()) {
                    Firebase.firestore
                        .collection(USER_COLLECTION).document(userId)
                        .collection(ENTRY_COLLECTION)
                        .document(entry.timestamp.toString())
                        .set(entry)
                        .addOnCompleteListener { onResult(it.exception) }
                } else {
                    it.documents.forEach() {
                        val oldEntry = it.toObject<JournalEntry>()!!
                        val updatedEntry = oldEntry.copy(
                            timestamp = entry.timestamp,
                            meditationName = entry.meditationName,
                            durationMin = oldEntry.durationMin + entry.durationMin,
                            durationSec = oldEntry.durationSec + entry.durationSec,
                            content = "${entry.content}\n\n> ${oldEntry.meditationName}\n${oldEntry.content}".trim()
                        )
                        deleteEntry(
                            userId =  userId,
                            timestamp = oldEntry.timestamp.toString(),
                            onResult = onResult
                        )
                        Firebase.firestore
                            .collection(USER_COLLECTION)
                            .document(userId)
                            .collection(ENTRY_COLLECTION)
                            .document(updatedEntry.timestamp.toString())
                            .set(updatedEntry)
                            .addOnCompleteListener { onResult(it.exception) }
                    }

                }
            }
            .addOnFailureListener { error -> onResult(error) }

    }

    override fun updateEntry(userId: String, entry: JournalEntry, onResult: (Throwable?) -> Unit) {
        Firebase.firestore
            .collection(USER_COLLECTION).document(userId)
            .collection(ENTRY_COLLECTION)
            .document(entry.timestamp.toString())
            .set(entry)
            .addOnCompleteListener { onResult(it.exception) }
    }

    override fun getEntry(
        userId: String,
        timestamp: String,
        onError: (Throwable) -> Unit,
        onSuccess: (JournalEntry) -> Unit
    ) {
        Firebase.firestore
            .collection(USER_COLLECTION).document(userId)
            .collection(ENTRY_COLLECTION)
            .document(timestamp)
            .get()
            .addOnFailureListener { error -> onError(error) }
            .addOnSuccessListener { result ->
                onSuccess(result.toObject<JournalEntry>() ?: JournalEntry())
            }
    }

    override fun deleteEntry(
        userId: String,
        timestamp: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore
            .collection(USER_COLLECTION).document(userId)
            .collection(ENTRY_COLLECTION)
            .document(timestamp)
            .delete()
            .addOnCompleteListener { onResult(it.exception) }
    }

    override fun deleteAllUserEntries() {
        TODO("Not yet implemented")
    }

    override fun getFavoriteMeditations(
        userId: String,
        onError: (Throwable?) -> Unit,
        onSuccess: (List<String>) -> Unit
    ) {
        Firebase.firestore
            .collection(USER_COLLECTION)
            .document(userId)
            .get()
            .addOnFailureListener { error -> onError(error) }
            .addOnSuccessListener { result ->
                val favoriteMeditations = result.get(FAVORITE_MEDITATIONS)
                onSuccess(favoriteMeditations as List<String>)
            }
    }

    companion object {
        private const val USER_COLLECTION = "users"
        private const val ENTRY_COLLECTION = "journalEntries"
        private const val FAVORITE_MEDITATIONS = "favoriteMeditations"
        const val ADD = "add"
        const val REMOVE = "remove"
        const val MODIFY = "modify"
    }
}