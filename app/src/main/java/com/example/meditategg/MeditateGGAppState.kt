package com.example.meditategg

import android.content.res.Resources
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.meditategg.common.snackbar.SnackbarManager
import com.example.meditategg.common.snackbar.SnackbarMessage.Companion.toMessage
import com.google.accompanist.systemuicontroller.SystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class MeditateGGAppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    val systemUiController: SystemUiController,
    val bottomBarVisible: MutableState<Boolean>,
    val currentScreen: MutableState<String>,
    private val snackbarManager: SnackbarManager,
    private val resources: Resources,
    coroutineScope: CoroutineScope
) {
    init {
        coroutineScope.launch {
            snackbarManager.snackbarMessages.filterNotNull().collect { snackbarMessage ->
                val text = snackbarMessage.toMessage(resources)
                scaffoldState.snackbarHostState.showSnackbar(text)
            }
        }
    }

    fun changeRoute(route: String) {
        currentScreen.value = route
        when (route) {
            MAP_SCREEN -> showBottomBar()
            JOURNAL_SCREEN -> showBottomBar()
            SETTINGS_SCREEN -> showBottomBar()
            else -> hideBottomBar()
        }
    }

    fun onMapNavigate() {
        if (currentScreen.value == JOURNAL_SCREEN) navigateAndPopUp(MAP_SCREEN, JOURNAL_SCREEN)
        else navigateAndPopUp(MAP_SCREEN, SETTINGS_SCREEN)
    }

    fun onJournalNavigate() {
        if (currentScreen.value == SETTINGS_SCREEN) navigateAndPopUp(JOURNAL_SCREEN, SETTINGS_SCREEN)
        else navigateAndPopUp(JOURNAL_SCREEN, MAP_SCREEN)
    }

    fun onSettingsNavigate() {
        if (currentScreen.value == JOURNAL_SCREEN) navigateAndPopUp(SETTINGS_SCREEN, JOURNAL_SCREEN)
        else navigateAndPopUp(SETTINGS_SCREEN, MAP_SCREEN)
    }

    fun changeSystemUiColor(color: Color) {
        systemUiController.setSystemBarsColor(color)
    }

    fun popUp() {
        navController.popBackStack()
    }

    fun navigate(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
        }
    }

    fun navigateAndPopUp(route: String, popUp: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(popUp) { inclusive = true }
        }
    }

    fun clearAndNavigate(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) { inclusive = true }
        }
    }

    private fun showBottomBar() { bottomBarVisible.value = true }
    private fun hideBottomBar() { bottomBarVisible.value = false }
}