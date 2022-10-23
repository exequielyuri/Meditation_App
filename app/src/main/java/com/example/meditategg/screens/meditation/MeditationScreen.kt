package com.example.meditategg.screens.meditation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.meditategg.R
import com.example.meditategg.common.composable.ExpandableCard
import com.example.meditategg.common.composable.GradientButton
import com.example.meditategg.screens.Screen
import com.example.meditategg.theme.*
import kotlinx.coroutines.delay
import java.text.DecimalFormat
import kotlin.time.Duration.Companion.seconds

@Composable
fun MeditationScreen(
    meditation: Meditation,
    navController: NavHostController,
    itemColor: Color = Purple50,
    backgroundGradient: Brush = Brush.verticalGradient(
        listOf(
            Color.White,
            Purple20
        )
    )
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundGradient),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        val openDialog = remember { mutableStateOf(false) }

        if (openDialog.value) {
            CompletionDialog(
                openDialog = openDialog
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp)
                .padding(horizontal = 25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {
                navController.navigate(Screen.MeditationMap.route)
            }) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = "Back",
                    tint = itemColor
                )
            }

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(R.drawable.info),
                    contentDescription = "More Info",
                    tint = itemColor
                )
            }
        }


        Text(
            text = meditation.name,
            color = itemColor,
            fontSize = 36.sp,
            fontFamily = Lora,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.h4.copy(
                shadow = Shadow(
                    color = Color(0xaa2c133c),
                    offset = Offset(0f, 3f),
                    blurRadius = 5f
                )
            )
        )

        Spacer(
            modifier = Modifier
                .height(15.dp)
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(5f)
                    .padding(horizontal = 30.dp)
            ) { items(
                items = meditation.instructions,
                itemContent = { item ->
                    Spacer(
                        modifier = Modifier
                            .height(2.dp)
                    )
                    ExpandableCard(instruction = item)
                })
            }

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ){
                var counting by remember { mutableStateOf(false) }
                var seconds by remember { mutableStateOf(0) }

                LaunchedEffect(key1 = seconds, key2 = counting) {
                    if(counting) {
                        delay(1.seconds)
                        seconds++
                    }
                }

                GradientButton(
                    text = if (counting) {
                            val df = DecimalFormat("00")
                            "${df.format(seconds/60)}:${df.format(seconds%60)}"
                        } else { "Meditate" },
                    textColor = Color.White,
                    fontSize = 18.sp,
                    fontFamily = Lora,
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.h4.copy(
                        shadow = Shadow(
                            color = Purple20,
                            offset = Offset(0f, 1f),
                            blurRadius = 1f
                        )
                    ),
                    gradient = Brush.horizontalGradient(
                        listOf(
                            Purple50,
                            Purple60
                        )
                    ),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 5.dp,
                        pressedElevation = 0.dp
                    ),
                    horizontalPad = 30.dp,
                    verticalPad = 10.dp,
                    onClick = {
                        if (counting) {
                            // record timer value
                            // open journal prompt
                            openDialog.value = true
                            seconds = 0
                        }
                        counting = !counting
                    }
                )
            }
        }
    }
}

@Composable
fun CompletionDialog(
    openDialog: MutableState<Boolean>
) {
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        confirmButton = {
            TextButton(
                onClick = {
                    openDialog.value = false
                }
            ) {
                Text(
                    text = "Done"
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    openDialog.value = false
                }
            ) {
                Text(
                    text = "Retry"
                )
            }
        },
        text = {
            var text by remember { mutableStateOf("") }

            Column {
                Text(
                    text = "You have meditated. Would you like to write about your experience?"
                )

                OutlinedTextField(
                    value = text,
                    onValueChange = {text = it},
                    maxLines = 4
                )
            }
        }
    )
}