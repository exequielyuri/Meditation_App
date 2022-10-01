package com.example.meditationapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    uiColor: Color,
    selectColor: Color,
    unselectColor: Color
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    BottomAppBar(
        cutoutShape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
        elevation = 40.dp,
        backgroundColor = uiColor
    ) {
        val journalSelected = "journal" == backStackEntry.value?.destination?.route
        val settingsSelected = "settings" == backStackEntry.value?.destination?.route
        val dashboardSelected = "dashboard" == backStackEntry.value?.destination?.route

        BottomNavigationItem(
            selected = journalSelected,
            onClick = { navController.navigate("journal") },
            selectedContentColor = selectColor,
            unselectedContentColor = unselectColor,
            icon = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Journal",
                        modifier = Modifier.size(25.dp)
                    )

                    if (journalSelected) {
                        Text(
                            text = "Journal",
                            textAlign = TextAlign.Center,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        )

        BottomNavigationItem(
            selected = false,
            enabled = false,
            onClick = {},
            icon = {})
        
        BottomNavigationItem(
            selected = settingsSelected,
            onClick = { navController.navigate("settings") },
            selectedContentColor = selectColor,
            unselectedContentColor = unselectColor,
            icon = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings",
                        modifier = Modifier.size(25.dp)
                    )

                    if (settingsSelected) {
                        Text(
                            text = "Settings",
                            textAlign = TextAlign.Center,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        )
    }
}