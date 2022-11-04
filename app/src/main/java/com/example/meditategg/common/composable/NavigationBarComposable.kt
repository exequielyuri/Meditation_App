package com.example.meditategg.common.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditategg.R.drawable as AppIcon
import com.example.meditategg.theme.Inter
import com.example.meditategg.theme.RoundedHexagonCutout

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    isVisible: MutableState<Boolean>,
    journalSelected: Boolean,
    settingsSelected: Boolean,
    onJournalNavigate: () -> Unit,
    onSettingsNavigate: () -> Unit
) {
    AnimatedVisibility(
        visible = isVisible.value,
        enter = slideInVertically(initialOffsetY = {it}),
        exit = slideOutVertically(targetOffsetY = {it})
    ) {
        BottomAppBar(
            modifier = modifier
                .graphicsLayer {
                    shape = RoundedCornerShape(
                        topStart = 13.dp,
                        topEnd = 13.dp
                    )
                    clip = true
                },
            cutoutShape = RoundedHexagonCutout,
            elevation = 4.dp,
            backgroundColor = MaterialTheme.colors.primary
        ) {
            BottomNavigationItem(
                selected = journalSelected,
                onClick = onJournalNavigate,
                selectedContentColor = MaterialTheme.colors.onPrimary,
                unselectedContentColor = MaterialTheme.colors.onSecondary,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(AppIcon.ic_journal),
                            contentDescription = "Journal",
                            modifier = Modifier.size(20.dp)
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
                onClick = onSettingsNavigate,
                selectedContentColor = MaterialTheme.colors.onPrimary,
                unselectedContentColor = MaterialTheme.colors.onSecondary,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(AppIcon.ic_settings),
                            contentDescription = "Settings",
                            modifier = Modifier.size(20.dp)
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