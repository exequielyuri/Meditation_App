package com.example.meditationapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.meditationapp.journal.Journal
import com.example.meditationapp.journal.JournalEntry
import com.example.meditationapp.ui.theme.*
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/*
 OutlinedButton(
        onClick = { /*TODO*/ },
        modifier = modifier
            .layoutId(name)
            .size(40.dp), // avoid oval
        shape = CircleShape,
        border = BorderStroke(5.dp, YellowLight),
        contentPadding = PaddingValues(0.dp), // avoid little icon
        colors = ButtonDefaults.buttonColors(backgroundColor = YellowDark)
        ) {

    }


FloatingActionButton(onClick = { navController.navigate("dashboard") }) {

}

*/

@Composable
fun Navigator() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()

    val uiColor = PurpleLightLight
    val borderColor = PurpleLight1
    val selectColor = PurpleLight1
    val unselectColor = Color.White
    systemUiController.setSystemBarsColor(uiColor)

    Scaffold(
        floatingActionButton = {
            val backStackEntry = navController.currentBackStackEntryAsState()
            val dashboardSelected = "dashboard" == backStackEntry.value?.destination?.route

            OutlinedButton(
                onClick = { navController.navigate("dashboard") },
                modifier = if (dashboardSelected) Modifier.size(75.dp) else Modifier.size(60.dp),
                shape = CircleShape,
                border = BorderStroke(10.dp, borderColor),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = if (dashboardSelected) selectColor else PurpleLightLight)
            ) {

            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                selectColor = selectColor,
                uiColor = uiColor,
                unselectColor = unselectColor
            )
        }
    ) {
        Navigation(navController = navController)
    }
}

@Composable
fun Navigation(navController: NavHostController, ) {

    NavHost(navController = navController, Screen.Dashboard.route) {
        composable(Screen.Dashboard.route) {
            Dashboard()
        }
        composable(Screen.Journal.route) {
            Journal()
        }
        composable(Screen.Settings.route) {
            Settings()
        }
    }

}