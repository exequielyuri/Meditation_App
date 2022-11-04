package com.example.meditategg.screens.sign_up

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.meditategg.LOGIN_SCREEN
import com.example.meditategg.SETTINGS_SCREEN
import com.example.meditategg.SIGN_UP_SCREEN
import com.example.meditategg.R.string as AppText
import com.example.meditategg.common.ext.isValidEmail
import com.example.meditategg.common.ext.isValidPassword
import com.example.meditategg.common.ext.passwordMatches
import com.example.meditategg.common.snackbar.SnackbarManager
import com.example.meditategg.model.service.AccountService
import com.example.meditategg.model.service.LogService
import com.example.meditategg.screens.MeditateGGViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.perf.ktx.performance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) : MeditateGGViewModel(logService) {
    var uiState = mutableStateOf(SignUpUiState())
        private set

    private val email get() = uiState.value.email
    private val password get() = uiState.value.password

    fun onNameChange(newValue: String) {
        uiState.value = uiState.value.copy(name = newValue)
    }

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        if (!password.isValidPassword()) {
            SnackbarManager.showMessage(AppText.password_error)
            return
        }

        if (!password.passwordMatches(uiState.value.repeatPassword)) {
            SnackbarManager.showMessage(AppText.password_match_error)
            return
        }

        viewModelScope.launch(showErrorExceptionHandler) {
            val createAccountTrace = Firebase.performance.newTrace(CREATE_ACCOUNT_TRACE)
            createAccountTrace.start()

            accountService.linkAccount(email, password) { error ->
                createAccountTrace.stop()

                if (error == null) {
                    openAndPopUp(SETTINGS_SCREEN, SIGN_UP_SCREEN)
                } else onError(error)
            }
        }
    }

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        openAndPopUp(LOGIN_SCREEN, SIGN_UP_SCREEN)
    }

    companion object {
        private const val CREATE_ACCOUNT_TRACE = "createAccount"
    }
}