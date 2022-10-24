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

    fun showBottomBar() {
        bottomBarVisible.value = true
    }

    fun hideBottomBar() {
        bottomBarVisible.value = false
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
}