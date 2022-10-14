package com.example.meditationapp.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
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
import com.example.meditationapp.BottomNavigationBar
import com.example.meditationapp.Screen
import com.example.meditationapp.Settings
import com.example.meditationapp.journal.Journal
import com.example.meditationapp.meditation.Meditation
import com.example.meditationapp.meditation.MeditationMap
import com.example.meditationapp.meditation.MeditationScreen
import com.example.meditationapp.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.example.meditationapp.R

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
                Button(
                    onClick = { navController.navigate("meditation_map") { launchSingleTop = true } },
                    modifier = Modifier
                        .width(65.dp)
                        .height(73.dp),
                    shape = RoundedHexagon,
                    contentPadding = PaddingValues(17.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = uiColor),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 3.dp,
                        pressedElevation = 0.dp
                    ),
                ) {
                    Icon(
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
    ) {
        Navigation(
            navController = navController,
            isVisible = isVisible
        )
    }

}

@Composable
fun Navigation(
    navController: NavHostController,
    isVisible: MutableState<Boolean>
) {

    NavHost(navController = navController, Screen.MeditationMap.route) {
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