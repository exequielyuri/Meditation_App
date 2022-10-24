package com.example.meditategg

import android.content.res.Resources
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.meditategg.screens.journal.Journal
import com.example.meditategg.screens.meditation.Meditation
import com.example.meditategg.screens.meditation.MeditationMap
import com.example.meditategg.screens.meditation.MeditationScreen
import com.example.meditategg.screens.settings.Settings
import com.example.meditategg.theme.MeditateGGTheme
import com.example.meditategg.theme.Purple40
import com.example.meditategg.theme.Purple50
import com.example.meditategg.theme.RoundedHexagon
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope

@Composable
fun MeditateGGApp() {
    MeditateGGTheme() {
        Surface(color = MaterialTheme.colors.background) {
            val appState = rememberAppState()

            val uiColor = Purple50
            val selectColor = Color.White
            val unselectColor = Purple40

            appState.changeSystemUiColor(uiColor)

            Scaffold(
                floatingActionButton = {
                    val backStackEntry = appState.navController.currentBackStackEntryAsState()
                    val mapSelected = MEDITATION_MAP_SCREEN == backStackEntry.value?.destination?.route

                    AnimatedVisibility(
                        visible = appState.bottomBarVisible.value,
                        enter = slideInVertically(initialOffsetY = {it}),
                        exit = slideOutVertically(targetOffsetY = {it})
                    ) {
                        FloatingActionButton(
                            onClick = { appState.navController.navigate(MEDITATION_MAP_SCREEN) { launchSingleTop = true } },
                            modifier = Modifier
                                .width(65.dp)
                                .height(73.dp),
                            shape = RoundedHexagon,
                            backgroundColor = uiColor
                        ) {
                            Icon(
                                modifier = Modifier.padding(17.dp),
                                painter = painterResource(R.drawable.lotus),
                                contentDescription = null,
                                tint = if (mapSelected) selectColor else unselectColor
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
                        selectColor = selectColor,
                        uiColor = uiColor,
                        unselectColor = unselectColor
                    )
                }
            ) { innerPaddingModifier ->
                NavHost(
                    navController = appState.navController,
                    startDestination = MEDITATION_MAP_SCREEN,
                    modifier = Modifier.padding(innerPaddingModifier)
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
    bottomBarVisible: MutableState<Boolean> = remember { mutableStateOf(true) },
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

fun NavGraphBuilder.meditateggGraph(appState: MeditateGGAppState, ) {
    composable(MEDITATION_MAP_SCREEN) {
        appState.bottomBarVisible.value = true
        MeditationMap(navController = appState.navController)
    }
    composable(JOURNAL_SCREEN) {
        appState.bottomBarVisible.value = true
        Journal()
    }
    composable(SETTINGS_SCREEN) {
        appState.bottomBarVisible.value = true
        Settings()
    }
    composable(
        route = "$MEDITATION_SCREEN$MEDITATION_NAME_ARG",
        arguments = listOf(navArgument(MEDITATION_NAME) { type = NavType.StringType })
    ) { entry ->
        appState.bottomBarVisible.value = false

        println("meditation name: ${entry.arguments?.getString(MEDITATION_NAME)}")
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