package com.example.meditategg.screens.journal

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import com.example.meditategg.common.snackbar.SnackbarManager
import com.example.meditategg.common.snackbar.SnackbarMessage
import com.example.meditategg.model.JournalEntry
import com.example.meditategg.model.service.AccountService
import com.example.meditategg.model.service.LogService
import com.example.meditategg.model.service.StorageService
import com.example.meditategg.model.service.impl.StorageServiceImpl.Companion.ADD
import com.example.meditategg.model.service.impl.StorageServiceImpl.Companion.REMOVE
import com.example.meditategg.screens.MeditateGGViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JournalViewModel @Inject constructor(
    logService: LogService,
    private val storageService: StorageService,
    private val accountService: AccountService
) : MeditateGGViewModel(logService) {
    var entries = mutableStateListOf<JournalEntry>()
        private set

    fun addListener() {
        viewModelScope.launch(showErrorExceptionHandler) {
            storageService.addJournalListener(
                accountService.getUserId(),
                ::onDocumentEvent,
                ::Error
            )
        }
    }

    fun removeListener() {
        viewModelScope.launch(showErrorExceptionHandler) {
            storageService.removeJournalListener()
        }
    }

    fun onBarClick(height: Int) {
        val durationInfo = SnackbarMessage.StringSnackbar(height.toString())
        SnackbarManager.showMessage(durationInfo)
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

    private fun onDocumentEvent(action: String, newEntry: JournalEntry) {
        when (action) {
            ADD -> entries.add(newEntry)
            REMOVE -> entries.remove(newEntry)
            else -> { entries[entries.indexOfFirst { entry -> entry.timestamp == newEntry.timestamp }] = newEntry }
        }
    }
}