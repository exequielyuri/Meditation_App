package com.example.meditategg.screens.journal

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import com.example.meditategg.model.JournalEntry
import com.example.meditategg.model.service.AccountService
import com.example.meditategg.model.service.LogService
import com.example.meditategg.model.service.StorageService
import com.example.meditategg.model.service.impl.StorageServiceImpl.Companion.ADD
import com.example.meditategg.model.service.impl.StorageServiceImpl.Companion.REMOVE
import com.example.meditategg.screens.MeditateGGViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class JournalViewModel @Inject constructor(
    logService: LogService,
    private val storageService: StorageService,
    private val accountService: AccountService
) : MeditateGGViewModel(logService) {
    var entries = mutableStateListOf<JournalEntry>()
        private set

    var weeklyEntries = mutableStateListOf<JournalEntry>()
        private set

    fun addListener() {
        viewModelScope.launch(showErrorExceptionHandler) {
            storageService.addJournalListener(
                accountService.getUserId(),
                ::onDocumentEvent,
                ::Error
            )
            delay(300L)
            updateWeeklyEntries()
        }
    }

    fun removeListener() {
        viewModelScope.launch(showErrorExceptionHandler) {
            storageService.removeJournalListener()
        }
    }

    fun onBarClick(duration: Int, context: Context) {
        val isSeconds = duration%60>1
        val durationMsg = if (duration/60 > 1)
        {
            if (isSeconds) "${duration / 60} minutes and ${duration % 60} seconds"
            else "${duration/60} minutes"
        } else if (duration/60 == 1) {
            if (isSeconds) "1 minute and ${duration%60} seconds"
            else "1 minute"
        } else {
            if (isSeconds) "$duration seconds"
            else "0 seconds"
        }
        Toast.makeText(context, durationMsg, Toast.LENGTH_SHORT).show()
    }

    fun onEditClick(entry: JournalEntry) {
        /*TODO: edit entry content in journal*/
    }

    fun onDeleteEntryClick(entry: JournalEntry) {
        viewModelScope.launch(showErrorExceptionHandler) {
            storageService.deleteEntry(
                accountService.getUserId(),
                entry.timestamp.toString()
            ) { error -> if (error != null) onError(error) }
        }
    }

    fun onBookmarkClick(entry: JournalEntry) {
        viewModelScope.launch(showErrorExceptionHandler) {
            val updatedEntry = entry.copy(bookmark = !entry.bookmark)

            storageService.updateEntry(
                accountService.getUserId(),
                updatedEntry
            ) { error -> if (error != null) onError(error) }
        }
    }

    private fun updateWeeklyEntries() {
        viewModelScope.launch {

        }
        if (entries.isEmpty()) {
            println("there are no entries so i return ")
            return
        }

        val recentEntries = mutableStateListOf<JournalEntry>()
        for (i in 6 downTo 0) {
            if (entries.size > i) recentEntries.add(entries.elementAt(i))
        }

        val entriesOfWeek = mutableStateListOf<JournalEntry>()

        val today = Calendar.getInstance()
        val dayOfWeek = today.get(Calendar.DAY_OF_WEEK)
        var day = today.get(Calendar.DAY_OF_MONTH)
        var month = today.get(Calendar.MONTH)
        var year = today.get(Calendar.YEAR)

        day = day + 1 - dayOfWeek
        val lastDayOfMonth = today.getActualMaximum(Calendar.DAY_OF_MONTH)

        for (i in 1..7) {
            var found = false
            run breaking@ {
                recentEntries.forEach() { entry ->
                    println("finding nemo ")
                    if (entry.day == day && entry.month == month && entry.year == year) {
                        entriesOfWeek.add(entry)
                        found = true
                        return@breaking
                    }
                }
            }
            if (!found) {
                entriesOfWeek.add(JournalEntry())
                println("added empty journal entry " + i)
            }

            if (day == lastDayOfMonth) {
                day = 1
                if (month == 12) {
                    month = 1
                    year++
                } else month++
            } else day++
        }

        weeklyEntries.addAll(entriesOfWeek)
    }

    private fun onDocumentEvent(action: String, newEntry: JournalEntry) {
        when (action) {
            ADD -> entries.add(newEntry)
            REMOVE -> entries.remove(newEntry)
            else -> { entries[entries.indexOfFirst { entry -> entry.timestamp == newEntry.timestamp }] = newEntry }
        }
    }
}