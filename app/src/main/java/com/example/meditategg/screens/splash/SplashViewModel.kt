package com.example.meditategg.screens.splash

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.meditategg.MAP_SCREEN
import com.example.meditategg.SPLASH_SCREEN
import com.example.meditategg.model.service.AccountService
import com.example.meditategg.model.service.LogService
import com.example.meditategg.screens.MeditateGGViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountService: AccountService,
    private val logService: LogService
) : MeditateGGViewModel(logService) {
    val showError = mutableStateOf(false)

    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        showError.value = false

        if (accountService.hasUser()) openAndPopUp(MAP_SCREEN, SPLASH_SCREEN)
        else createAnonymousAccount(openAndPopUp)
    }

    private fun createAnonymousAccount(openAndPopUp: (String, String) -> Unit) {
        viewModelScope.launch(logErrorExceptionHandler) {
            accountService.createAnonymousAccount { error ->
                if (error != null) {
                    showError.value = true
                    logService.logNonFatalCrash(error)
                }
                else openAndPopUp(MAP_SCREEN, SPLASH_SCREEN)
            }
        }
    }
}