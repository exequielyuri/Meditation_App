package com.example.meditategg.screens.meditation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.meditategg.R.string as AppText
import com.example.meditategg.common.snackbar.SnackbarManager
import com.example.meditategg.model.JournalEntry
import com.example.meditategg.model.service.AccountService
import com.example.meditategg.model.service.LogService
import com.example.meditategg.model.service.StorageService
import com.example.meditategg.screens.MeditateGGViewModel
import com.google.firebase.Timestamp
import com.google.firebase.ktx.Firebase
import com.google.firebase.perf.ktx.performance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MeditationViewModel @Inject constructor(
    logService: LogService,
    private val accountService: AccountService,
    private val storageService: StorageService
) : MeditateGGViewModel(logService) {
    var uiState = mutableStateOf(MeditationUiState())
        private set

    var entry = mutableStateOf(JournalEntry())
        private set

    private val durationSec get() = uiState.value.durationSec
    private val meditating get() = uiState.value.meditating
    private val openDialog get() = uiState.value.openDialog
    private val userReflection get() = uiState.value.userReflection

    fun incrementTimer() {
        uiState.value = uiState.value.copy(durationSec = durationSec+1)
    }

    fun toggleTimer() {
        uiState.value = uiState.value.copy(meditating = !meditating)
    }

    fun toggleDialog() {
        uiState.value = uiState.value.copy(openDialog = !openDialog)
    }

    fun onMeditationDone() {
        toggleDialog()
        entry.value = entry.value.copy(
            durationMin = durationSec/60,
            durationSec = durationSec%60
        )
        resetInputs()
    }

    fun onRetry() {
        toggleDialog()
        resetInputs()
    }

    fun onConfirmClick() {
        toggleDialog()
        entry.value = entry.value.copy(
            timestamp = Timestamp.now(),
            content = userReflection
        )
        viewModelScope.launch(showErrorExceptionHandler) {
            val saveEntryTrace = Firebase.performance.newTrace(SAVE_ENTRY_TRACE)
            saveEntryTrace.start()

            storageService.saveEntry(accountService.getUserId(), entry.value) { error ->
                saveEntryTrace.stop()
                if (error == null) {
                    SnackbarManager.showMessage(AppText.sync_success)
                } else onError(error)
            }
        }
    }

    fun onReflectionChange(newValue: String) {
        uiState.value = uiState.value.copy(userReflection = newValue)
    }

    fun initNewEntry(meditationName: String) {
        viewModelScope.launch {
            val today = Calendar.getInstance()
            entry.value = entry.value.copy(
                dayOfWeek = today.get(Calendar.DAY_OF_WEEK),
                day = today.get(Calendar.DAY_OF_MONTH),
                month = today.get(Calendar.MONTH),
                year = today.get(Calendar.YEAR),
                meditationName = meditationName
            )
        }
    }

    private fun resetInputs() {
        uiState.value = uiState.value.copy(
            durationSec = 0,
            userReflection = ""
        )
    }

    companion object {
        private const val SAVE_ENTRY_TRACE = "saveEntry"
    }
}