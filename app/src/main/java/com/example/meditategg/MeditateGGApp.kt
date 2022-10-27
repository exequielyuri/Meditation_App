package com.example.meditategg

import android.annotation.SuppressLint
import android.content.res.Resources
import androidx.compose.animation.*
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.meditategg.common.composable.BottomNavigationBar
import com.example.meditategg.common.snackbar.SnackbarManager
import com.example.meditategg.screens.login.LoginScreen
import com.example.meditategg.model.Meditation
import com.example.meditategg.screens.journal.JournalScreen
import com.example.meditategg.screens.map.MapScreen
import com.example.meditategg.screens.meditation.MeditationScreen
import com.example.meditategg.screens.settings.SettingsScreen
import com.example.meditategg.screens.sign_up.SignUpScreen
import com.example.meditategg.screens.splash.SplashScreen
import com.example.meditategg.theme.MeditateGGTheme
import com.example.meditategg.theme.RoundedHexagon
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MeditateGGApp() {
    MeditateGGTheme() {
        Surface(color = MaterialTheme.colors.background) {

            val appState = rememberAppState()

            appState.changeSystemUiColor(MaterialTheme.colors.primary)

            Scaffold(
                scaffoldState = appState.scaffoldState,
                snackbarHost = {
                    SnackbarHost(
                        hostState = it,
                        modifier = Modifier.padding(8.dp),
                        snackbar = { snackbarData ->
                            Snackbar(
                                snackbarData,
                                contentColor = MaterialTheme.colors.onPrimary
                            )
                        }
                    )
                },
                floatingActionButton = {
                    val backStackEntry = appState.navController.currentBackStackEntryAsState()
                    val mapSelected = MAP_SCREEN == backStackEntry.value?.destination?.route

                    AnimatedVisibility(
                        visible = appState.bottomBarVisible.value,
                        enter = slideInVertically(initialOffsetY = {it}),
                        exit = slideOutVertically(targetOffsetY = {it})
                    ) {
                        FloatingActionButton(
                            onClick = { appState.clearAndNavigate(MAP_SCREEN)},
                            modifier = Modifier
                                .width(65.dp)
                                .height(73.dp),
                            shape = RoundedHexagon,
                            backgroundColor = MaterialTheme.colors.primary
                        ) {
                            Icon(
                                modifier = Modifier.padding(17.dp),
                                painter = painterResource(R.drawable.ic_lotus),
                                contentDescription = null,
                                tint = if (mapSelected) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSecondary
                            )
                        }
                    }
                },
                floatingActionButtonPosition = FabPosition.Center,
                isFloatingActionButtonDocked = true,
                bottomBar = {
                    BottomNavigationBar(
                        navController = appState.navController,
                        isVisible = appState.bottomBarVisible,
                        selectColor = MaterialTheme.colors.onPrimary,
                        uiColor = MaterialTheme.colors.primary,
                        unselectColor = MaterialTheme.colors.onSecondary
                    )
                }
            ) {
                NavHost(
                    navController = appState.navController,
                    startDestination = SPLASH_SCREEN,
                ) { meditateggGraph(appState) }
            }
        }
    }
}

@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    systemUiController: SystemUiController = rememberSystemUiController(),
    bottomBarVisible: MutableState<Boolean> = remember { mutableStateOf(false) },
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) = remember(scaffoldState, navController, snackbarManager, resources, coroutineScope) {
    MeditateGGAppState(scaffoldState, navController, systemUiController, bottomBarVisible, snackbarManager, resources, coroutineScope)
}

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

fun NavGraphBuilder.meditateggGraph(appState: MeditateGGAppState) {
    composable(MAP_SCREEN) {
        appState.showBottomBar()
        MapScreen(navController = appState.navController)
    }
    composable(JOURNAL_SCREEN) {
        JournalScreen()
    }
    composable(SETTINGS_SCREEN) {
        SettingsScreen(
            restartApp = { route -> appState.clearAndNavigate(route) },
            openScreen = { route -> appState.navigate(route) }
        )
    }
    composable(LOGIN_SCREEN) {
        appState.hideBottomBar()
        LoginScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }
    composable(SIGN_UP_SCREEN) {
        appState.hideBottomBar()
        SignUpScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }
    composable(SPLASH_SCREEN) {
        appState.hideBottomBar()
        SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }
    composable(
        route = "$MEDITATION_SCREEN$MEDITATION_NAME_ARG",
        arguments = listOf(navArgument(MEDITATION_NAME) { type = NavType.StringType })
    ) { entry ->
        appState.hideBottomBar()

        val meditation = when(entry.arguments?.getString(MEDITATION_NAME)) {
            "Posture" -> Meditation.Posture
            "Trataka" -> Meditation.Trataka
            "Bee's Breath" -> Meditation.BeesBreath
            "Mindfulness" -> Meditation.Mindfulness
            "Ice" -> Meditation.Ice
            "Third Eye" -> Meditation.ThirdEye
            else -> Meditation.Custom
        }
        MeditationScreen(
            meditation = meditation,
            navController = appState.navController
        )
    }
}