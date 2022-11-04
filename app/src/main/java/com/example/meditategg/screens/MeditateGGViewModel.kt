package com.example.meditategg.screens

import androidx.lifecycle.ViewModel
import com.example.meditategg.common.snackbar.SnackbarManager
import com.example.meditategg.common.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import com.example.meditategg.model.service.LogService
import kotlinx.coroutines.CoroutineExceptionHandler

open class MeditateGGViewModel(private val logService: LogService) : ViewModel() {
    open val showErrorExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }

    open val logErrorExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        logService.logNonFatalCrash(throwable)
    }

    open fun onError(error: Throwable) {
        SnackbarManager.showMessage(error.toSnackbarMessage())
        logService.logNonFatalCrash(error)
    }
}