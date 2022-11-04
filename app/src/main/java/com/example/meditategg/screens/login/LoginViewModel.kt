package com.example.meditategg.screens.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.meditategg.LOGIN_SCREEN
import com.example.meditategg.SETTINGS_SCREEN
import com.example.meditategg.SIGN_UP_SCREEN
import com.example.meditategg.R.string as AppText
import com.example.meditategg.common.ext.isValidEmail
import com.example.meditategg.common.snackbar.SnackbarManager
import com.example.meditategg.model.service.AccountService
import com.example.meditategg.model.service.LogService
import com.example.meditategg.screens.MeditateGGViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) : MeditateGGViewModel(logService) {
    var uiState = mutableStateOf(LoginUiState())
        private set

    private val email get() = uiState.value.email
    private val password get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        if (password.isBlank()) {
            SnackbarManager.showMessage(AppText.empty_password_error)
            return
        }

        viewModelScope.launch(showErrorExceptionHandler) {
            accountService.authenticate(email, password) { error ->
                if (error == null) {
                    openAndPopUp(SETTINGS_SCREEN, LOGIN_SCREEN)
                } else onError(error)
            }
        }
    }

    fun onForgotPasswordClick() {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        viewModelScope.launch(showErrorExceptionHandler) {
            accountService.sendRecoveryEmail(email) { error ->
                if (error != null) onError(error)
                else SnackbarManager.showMessage(AppText.recovery_email_sent)
            }
        }
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        openAndPopUp(SIGN_UP_SCREEN, LOGIN_SCREEN)
    }
}