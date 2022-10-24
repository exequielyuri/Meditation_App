package com.example.meditategg.common.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.meditategg.JOURNAL_SCREEN
import com.example.meditategg.R
import com.example.meditategg.SETTINGS_SCREEN
import com.example.meditategg.theme.Inter
import com.example.meditategg.theme.RoundedHexagonCutout

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    isVisible: MutableState<Boolean>,
    uiColor: Color,
    selectColor: Color,
    unselectColor: Color
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    AnimatedVisibility(
        visible = isVisible.value,
        enter = slideInVertically(initialOffsetY = {it}),
        exit = slideOutVertically(targetOffsetY = {it})
    ) {
        BottomAppBar(
            modifier = Modifier
                .graphicsLayer {
                    shape = RoundedCornerShape(
                        topStart = 13.dp,
                        topEnd = 13.dp
                    )
                    clip = true
                },
            cutoutShape = RoundedHexagonCutout,
            elevation = 4.dp,
            backgroundColor = uiColor
        ) {
            val journalSelected = JOURNAL_SCREEN == backStackEntry.value?.destination?.route
            val settingsSelected = SETTINGS_SCREEN == backStackEntry.value?.destination?.route

            BottomNavigationItem(
                selected = journalSelected,
                onClick = { navController.navigate(JOURNAL_SCREEN) { launchSingleTop = true } },
                selectedContentColor = selectColor,
                unselectedContentColor = unselectColor,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(R.drawable.journal),
                            contentDescription = "Journal",
                            modifier = Modifier.size(25.dp)
                        )

                        if (journalSelected) {
                            Text(
                                text = "Journal",
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp,
                                fontFamily = Inter
                            )
                        }
                    }
                }
            )

            BottomNavigationItem(
                selected = false,
                enabled = false,
                onClick = {},
                icon = {}
            )

            BottomNavigationItem(
                selected = settingsSelected,
                onClick = {
                    navController.navigate(SETTINGS_SCREEN) {
                        launchSingleTop = true
                    }},
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
                                fontSize = 12.sp,
                                fontFamily = Inter
                            )
                        }
                    }
                }
            )
        }
    }
}