package com.example.meditategg.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.meditategg.BottomNavigationBar
import com.example.meditategg.screens.Screen
import com.example.meditategg.screens.journal.Journal
import com.example.meditategg.screens.meditation.Meditation
import com.example.meditategg.screens.meditation.MeditationMap
import com.example.meditategg.screens.meditation.MeditationScreen
import com.example.meditategg.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.example.meditategg.R
import com.example.meditategg.screens.settings.Settings

@Composable
fun Navigator() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()

    val uiColor = Purple50
    val selectColor = Color.White
    val unselectColor = Purple40

    systemUiController.setSystemBarsColor(uiColor)

    val isVisible = rememberSaveable { mutableStateOf(true) }

    Scaffold(
        floatingActionButton = {
            val backStackEntry = navController.currentBackStackEntryAsState()
            val mapSelected = "meditation_map" == backStackEntry.value?.destination?.route

            AnimatedVisibility(
                visible = isVisible.value,
                enter = slideInVertically(initialOffsetY = {it}),
                exit = slideOutVertically(targetOffsetY = {it})
            ) {
                FloatingActionButton(
                    onClick = { navController.navigate("meditation_map") { launchSingleTop = true } },
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
                navController = navController,
                isVisible = isVisible,
                selectColor = selectColor,
                uiColor = uiColor,
                unselectColor = unselectColor
            )
        }
    ) { innerPaddingModifier ->
        Navigation(
            navController = navController,
            isVisible = isVisible,
            modifier = Modifier.padding(innerPaddingModifier)
        )
    }

}

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    isVisible: MutableState<Boolean>
) {

    NavHost(
        navController = navController,
        startDestination = Screen.MeditationMap.route,
        modifier = modifier,
    ) {
        composable(Screen.MeditationMap.route) {
            isVisible.value = true
            MeditationMap(navController = navController)
        }
        composable(Screen.Journal.route) {
            isVisible.value = true
            Journal()
        }
        composable(Screen.Settings.route) {
            isVisible.value = true
            Settings()
        }
        composable(
            route = Screen.MeditationScreen.route + "/{meditationName}",
            arguments = listOf(
                navArgument("meditationName") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            isVisible.value = false
            val meditation = when(entry.arguments?.getString("meditationName")) {
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
                navController = navController
            )
        }
    }

}